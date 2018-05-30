package com.partha.userService.entities;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="users")
public class User {
	

	@Id
	//@GeneratedValue
	private Integer id;
	
	private String username;
	private String password;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean enabled;
	private boolean credentialsNonExpired;
	
//	@Transient
//	private List<S> authorities;
	

//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return this.authorities;
//	}
	
	public Integer getId() {
		return this.id;
	}
	
	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	public boolean isEnabled() {
		return this.enabled;
	}

	public User() {
		super();
	}
	
	
	

}
