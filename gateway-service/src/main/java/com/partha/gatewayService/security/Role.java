package com.partha.gatewayService.security;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority{
	
	private static final long serialVersionUID = 1L;
	private String username;
	private String authority;

	public Role(String username, String authority) {
		this.username=username;
		this.authority=authority;
	}
	
	public Role() {
		super();
	}

	public String getAuthority() {
		return this.authority;
	}

	public String getUsername() {
		return this.username;
	}


}
