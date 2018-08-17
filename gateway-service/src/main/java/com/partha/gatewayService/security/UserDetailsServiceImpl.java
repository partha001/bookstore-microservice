package com.partha.gatewayService.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.partha.gatewayService.bo.Authority;
import com.partha.gatewayService.bo.User;
import com.partha.gatewayService.restclients.UserClient;

@Component
public class UserDetailsServiceImpl implements UserDetailsService{


	@Autowired
	private UserClient userClient;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetailsImpl userDetails=null;
		try{
			User user=userClient.findByUsername(username);
			if(user!=null){
				userDetails=new UserDetailsImpl();
				userDetails.setUsername(user.getUsername());
				userDetails.setPassword(user.getPassword());
				userDetails.setFirstName(user.getFirstname());
				userDetails.setLastName(user.getLastname());
				userDetails.setAccountNonExpired(user.isAccountNonExpired());
				userDetails.setAccountNonLocked(user.isAccountNonLocked());
				userDetails.setCredentialsNonExpired(user.isCredentialsNonExpired());
				userDetails.setEnabled(user.isEnabled());

				List<Role> roles = new ArrayList<Role>();
				Role role =null;
				for(Authority authority:user.getAuthorities()){
					role =new Role(user.getUsername(),authority.getAuthority());
					roles.add(role);
				}		
				userDetails.setAuthorities(roles);			
			}
			return userDetails;
		}catch(Exception ex){
			throw new UsernameNotFoundException(ex.toString());
		}
	}

}
