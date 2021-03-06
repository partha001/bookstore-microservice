package com.partha.gatewayService.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private UserDetailsService userDetailsServiceImpl;
	
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        
   
          UserDetails user = userDetailsServiceImpl.loadUserByUsername(username);
   
          if (user == null || !user.getUsername().equalsIgnoreCase(username)) {
              throw new BadCredentialsException("Username not found.");
          }
   
       
          if (!encoder.matches(password, user.getPassword())) {
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
