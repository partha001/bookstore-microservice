package com.partha.gatewayService.config;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.partha.gatewayService.security.CustomAuthenticationProvider;
import com.partha.gatewayService.security.CustomBasicAuthenticationEntryPoint;
import com.partha.gatewayService.security.CustomInmemoryAuthenticationProvider;


/**
 * to use this class with inmemory authentication
 * 1.uncomment method userDetailsService() in this class with @Bean annotaion
 * 2.comment annotation @component from UserDetailsServiceImpl class
 * 3.comment @autowired CustomAuthenticationProvider
 * 4.comment method configure(AuthenticationManagerBuilder auth)
 * 
 * to switch to database authentication is the opposite
 * @author partha
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PasswordEncoder encoder;
	
	
//	 //for in-memory authentication	
//		@Bean
//		public UserDetailsService userDetailsService() {
//			InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//			manager.createUser(User.withUsername("partha").password(encoder().encode("partha")).roles("ADMIN").build());
//			//manager.createUser(User.withUsername("test").password(encoder().encode("test")).roles("USER").build());
//			return manager;
//		}
	
			
	@Autowired
	//@Qualifier("authenticationProvider")
	private CustomAuthenticationProvider customeAuthenticationProvider;
	
//	@Autowired
//	@Qualifier("customeAuthenticationProvider2")
//	private CustomAuthenticationProvider customeAuthenticationProvider2;


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// to configure using provider bean		
		auth.authenticationProvider(customeAuthenticationProvider);
		// the below authentication provider has been registered to test the api
		//gateway without being dependent on other microservices
		auth.authenticationProvider(new CustomInmemoryAuthenticationProvider());	
		//auth.inMemoryAuthentication()
		//.withUser("partha").password("partha").roles("USER");
	}


	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}


	@Autowired
	private CustomBasicAuthenticationEntryPoint customBasicAuthenticationEntryPoint;


	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
		//adding cors by default it looks for a bean with name corsConfigurationSource
		.cors()
		//disabling csrf
		.and().csrf().disable()
		.authorizeRequests()
		.antMatchers("/login",
				"/logout",
				"/test1",
				"/test2",
				"/test3",
				"/partners",
				"/authenticationFailed",
				"/entrypoint",
				"/api/userService/users/register",
				
				//comment this block to securet the below protected endpoints start
				"/updates",
				
				//comment this block to securet the below protected endpoints end
				
				"/api/userService/users/checkUsernameAvailability").permitAll()
		.antMatchers("/home").hasAnyRole("USER","ADMIN")
		.anyRequest()
		.authenticated()
		.and().httpBasic().authenticationEntryPoint(customBasicAuthenticationEntryPoint)
		.and().addFilter(basicAuthenticationFilter(super.authenticationManagerBean()))
		.logout()
		.invalidateHttpSession(true)
		.logoutSuccessHandler(logoutSuccessHandler())
		.deleteCookies("JSESSIONID");
		//else without this user will be redirected to login page by default configuration
		//.and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())
	}


	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();      
		//config.addAllowedOrigin("*");
		config.setAllowedOrigins(Arrays.asList("http://localhost:4200",""));
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		//configuration.setAllowedMethods(Arrays.asList("GET","POST"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}


	private BasicAuthenticationFilter basicAuthenticationFilter(AuthenticationManager authManager) throws Exception {
		BasicAuthenticationFilter basicAuthenticationFilter = new BasicAuthenticationFilter(authManager,
				customBasicAuthenticationEntryPoint);
		return basicAuthenticationFilter;
	}


	private LogoutSuccessHandler logoutSuccessHandler(){
		return new LogoutSuccessHandler() {

			@Override
			public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
					throws IOException, ServletException {
				response.setStatus(204);		
			}
		};
	}


	//	@Bean
	//	public NoOpPasswordEncoder encoder() {
	//		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	//	}

	

	//	private AuthenticationFailureHandler failureHandler(){
	//		return new AuthenticationFailureHandler() {
	//			@Override
	//			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
	//					AuthenticationException exception) throws IOException, ServletException {
	//				System.out.println("inside failure handler");
	//				System.out.println("requested uri :"+request.getRequestURI());
	//				response.sendRedirect("/authenticationFailed");
	//			}
	//		};
	//	}



	//	private AuthenticationEntryPoint authenticationEntryPoint(){
	//		return new AuthenticationEntryPoint(){
	//			@Override
	//			public void commence(HttpServletRequest request, HttpServletResponse response,
	//					AuthenticationException authException) throws IOException, ServletException {
	//				System.out.println("inside authenticationEntryPoint");
	//				System.out.println("requested uri :"+request.getRequestURI());
	//				response.sendRedirect("/entrypoint");				
	//			}
	//		};		
	//	}



}
