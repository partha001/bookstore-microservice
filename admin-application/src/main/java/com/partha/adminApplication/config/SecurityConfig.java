package com.partha.adminApplication.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String LOGIN_FAILURE_URL="/login-error";

		@Autowired
		BCryptPasswordEncoder encoder;
		
		@Bean
		public BCryptPasswordEncoder encoder(){
			return new BCryptPasswordEncoder();
		}

	@Autowired
	public void configGlobal(AuthenticationManagerBuilder auth)throws Exception{
		auth.inMemoryAuthentication()
		.withUser("admin").password(encoder.encode("admin")).roles("USER");
	}

	//url of pages which are not secured will go inside this
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers(LOGIN_FAILURE_URL);
	}


	//one can play around over these configurations 
	protected void configure(HttpSecurity http) throws Exception{
				http.cors().disable().
				csrf().disable()
				.authorizeRequests()
					.antMatchers("/mylogin"
							,"/"
							,"/index"
							,"/login-error"
							,"/css/**"
							,"/js/**"
							,"/images/**").permitAll()
					.anyRequest().authenticated()	
					.and()
				.formLogin()
					.loginPage("/index")
					.defaultSuccessUrl("/home",true)
					.failureUrl(LOGIN_FAILURE_URL);
					//.failureForwardUrl("/login-error");
				
	}


	public AuthenticationEntryPoint authenticationEntryPoint(){
		return new AuthenticationEntryPoint() {
			
			@Override
			public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException ex)
					throws IOException, ServletException {
				System.out.println("hello world");
			}
			
		};
	}


}
