package com.partha.gatewayService.config;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.partha.gatewayService.security.CustomAuthenticationProvider;
import com.partha.gatewayService.security.CustomBasicAuthenticationEntryPoint;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

// //for in-memory authentication	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//		manager.createUser(User.withUsername("partha").password(encoder().encode("partha")).roles("ADMIN").build());
//		//manager.createUser(User.withUsername("test").password(encoder().encode("test")).roles("USER").build());
//		return manager;
//	}
	
	
	@Autowired
	private CustomAuthenticationProvider provider;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 auth.authenticationProvider(provider);
		
		
//	    auth.authenticationProvider(domainUsernamePasswordAuthenticationProvider()).
//	            authenticationProvider(backendAdminUsernamePasswordAuthenticationProvider()).
//	            authenticationProvider(tokenAuthenticationProvider());
	}
	

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}


	//	@Bean
	//	public NoOpPasswordEncoder encoder() {
	//		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	//	}


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
				"/api/userService/users/checkUsernameAvailability").permitAll()
		.antMatchers("/home").hasRole("ADMIN")
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


		//		//for form login
		//		http
		//		//adding cors by default it looks for a bean with name corsConfigurationSource
		//		.cors().and()
		//		.authorizeRequests()
		//		.antMatchers("/login",
		//				"/test3",
		//				"/partners",
		//				"/authenticationFailed",
		//				"/entrypoint",
		//				"/api/userService/users/register",
		//				"/api/userService/users/checkUsernameAvailability").permitAll()
		//		.antMatchers("/home").hasRole("ADMIN")
		//		.anyRequest()
		//		.authenticated()
		//		.and()
		//		.formLogin()	
		//		//.loginPage("/login")
		//		//.defaultSuccessUrl("/home",false)
		//		.and().formLogin().successHandler(successHandler())
		//		//will be invoked if authencation fails
		//		.failureHandler(failureHandler())
		//		//will be invoked if there are other exceptions like resource not found,etc
		//		//else without this user will be redirected to login page by default configuration
		//		.and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())
		//		//disabling csrf
		//		.and().csrf().disable();						
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



	//	public AuthenticationSuccessHandler successHandler(){
	//
	//		return new AuthenticationSuccessHandler(){
	//
	//			@Override
	//			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
	//					Authentication authentication) throws IOException, ServletException {
	//
	//				//request redirection
	//				//response.sendRedirect("/home");
	//				
	//
	////				//request forwarding doesnt work 
	////				RequestDispatcher dispatcher = request.getRequestDispatcher("/home");
	////				dispatcher.forward(request, response);
	//
	//
	//				response.setContentType("application/json");
	//			    PrintWriter out = response.getWriter();
	//			    JSONObject result = new JSONObject();
	//			    JSONObject obj= new JSONObject(authentication.getPrincipal());
	//			    result.put("principal", obj);
	//			    result.put("authenticated", true);
	//			    obj= new JSONObject();
	//			    obj.put("remoteAddress", "");
	//			    obj.put("abc", request.getRequestedSessionId());
	//			    result.put("details", obj);
	//			 System.out.println("sessionid"+request.getRequestedSessionId());
	//			    result.put("abc", request.getRequestedSessionId());
	//			    out.println(result.toString());
	//
	//			}
	//			
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
