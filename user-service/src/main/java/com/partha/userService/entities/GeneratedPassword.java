package com.partha.userService.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name="generatedPassword")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeneratedPassword {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userId")
	@JsonIgnore
	private User user;
	
	@Column(name="generatedPassword")
	private String generatedPassword;
	
	@Temporal(TemporalType.TIMESTAMP) 
	@Column(name="generationDate")
	private Date generationDate;
	
	
	@Column(name="active")
	private boolean active;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getGeneratedPassword() {
		return generatedPassword;
	}


	public void setGeneratedPassword(String generatedPassword) {
		this.generatedPassword = generatedPassword;
	}


	public Date getGenerationDate() {
		return generationDate;
	}


	public void setGenerationDate(Date generationDate) {
		this.generationDate = generationDate;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}
	

}
