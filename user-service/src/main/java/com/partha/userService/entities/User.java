package com.partha.userService.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;


@Entity
@Table(name="users")
@Builder
@AllArgsConstructor
public class User {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String firstname;
	private String lastname;
	
	//username is nothing but the email
	private String username;
	private String password;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean enabled;
	private boolean credentialsNonExpired;
	private String securityQuestion;
	private String securityAnswer;
	
	
	@OneToMany(fetch=FetchType.LAZY ,cascade=CascadeType.ALL, mappedBy="user")
	private List<Authority> authorities;
	
	@OneToMany(fetch=FetchType.LAZY ,cascade=CascadeType.ALL, mappedBy="user")
	private List<GeneratedPassword> generatedPasswords;
	
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

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	public List<GeneratedPassword> getGeneratedPasswords() {
		return generatedPasswords;
	}

	public void setGeneratedPasswords(List<GeneratedPassword> generatedPasswords) {
		this.generatedPasswords = generatedPasswords;
	}
	
}
