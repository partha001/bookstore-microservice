package com.partha.gatewayService.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.partha.gatewayService.security.CustomAuthenticationProvider;
import com.partha.gatewayService.security.JwtAuthenticationEntryPoint;
import com.partha.gatewayService.security.JwtAuthenticationFilter;


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
			
	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;
	
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// to configure using provider bean		
		auth.authenticationProvider(customAuthenticationProvider);
		// the below authentication provider has been registered to test the api
		//gateway without being dependent on other microservices
		//auth.authenticationProvider(new CustomInmemoryAuthenticationProvider());	
		//auth.inMemoryAuthentication()
		//.withUser("partha").password("partha").roles("USER");
	}


	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}



	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
		//adding cors by default it looks for a bean with name corsConfigurationSource
		.cors()	
			.and()
		//disabling csrf
		.csrf().disable()
		//redirections for exception
		.exceptionHandling()
		.authenticationEntryPoint(jwtAuthenticationEntryPoint)
			.and()
		//session management
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
		.authorizeRequests()
		.antMatchers("/login",
						"/health",
						"/partners",
						"/authenticationFailed",
						"/api/userService/users/register",
						"/api/userService/users/{\\d+}/forgotPassword",
						"/api/userService/users/{\\d+}/generatePassword",
						
						//comment this block to secure the below protected endpoints start
						//"/updates",
						"/api/productService/books",
						"/logout",
						//comment this block to securet the below protected endpoints end
						
						//actuator endpoints
						"/actuator/health",
						
						
						"/api/userService/users/checkUsernameAvailability"
				).permitAll()
		.antMatchers("/home").hasAnyRole("USER","ADMIN")
		.anyRequest().authenticated()
		//.and().httpBasic().authenticationEntryPoint(customBasicAuthenticationEntryPoint)
		//.and().addFilter(basicAuthenticationFilter(super.authenticationManagerBean()))
		.and().addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		
//		.and()
//		.logout()
//		.invalidateHttpSession(true)
//		.logoutSuccessHandler(logoutSuccessHandler())
//		.deleteCookies("JSESSIONID");
		//else without this user will be redirected to login page by default configuration
		//.and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())
	}

	
	

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}


	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();      
		config.addAllowedOrigin("*");
		//config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		config.setAllowCredentials(true);
		//configuration.setAllowedMethods(Arrays.asList("GET","POST"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}


}
