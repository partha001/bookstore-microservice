package com.partha.userService.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="addressDetail")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDetails {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;
	
	@Column(name="addressLine1")
	private String addressLine1;
	
	@Column(name="addressLine2")
	private String addressLine2;
	
	@Column(name="state")
	private String state;
	
	@Column(name="country")
	private String country;
	
	@Column(name="pincode")
	private Integer pincode;
	
	@Temporal(TemporalType.TIMESTAMP) 
	@Column(name="insertDate")
	private Date insertDate;
	
	@Temporal(TemporalType.TIMESTAMP) 
	@Column(name="updateDate")
	private Date updateDate;

}
