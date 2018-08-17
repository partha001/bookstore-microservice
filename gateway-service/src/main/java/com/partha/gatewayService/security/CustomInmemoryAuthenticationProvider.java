package com.partha.gatewayService.security;

import java.util.Collection;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author partha biswas
 * @description : this authentication provider is basically to test the application 
 * 					without depending on other microservices
 */
public class CustomInmemoryAuthenticationProvider implements AuthenticationProvider{


	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("partha").password("partha").roles("USER","ADMIN").build());
		manager.createUser(User.withUsername("mimi").password("mimi").roles("USER").build());
		return manager;
	}


	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		UserDetails user = userDetailsService().loadUserByUsername(username);
		if (!user.getUsername().equalsIgnoreCase(username)) {
			throw new BadCredentialsException("Username not found.");
		}
		if (!password.equals(user.getPassword())) {
			throw new BadCredentialsException("Wrong password.");
		}
		Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
		return new UsernamePasswordAuthenticationToken(user, password, authorities);
	}
	

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
