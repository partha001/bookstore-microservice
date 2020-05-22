package com.partha.productService.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="cart")
public class CartItem {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer cartId;
	

	@ManyToOne
	@JoinColumn(name="bookId")
	private Book book;
	
	@Column(name="bookId",insertable=false,updatable=false)
	private Integer bookId;
		
	@Column(name="userId")
	private Integer userId;
	
	@Column(name="quantity")
	private Integer quantity;
	
	@Column(name="active")
	private Boolean active;
	
	@Temporal(TemporalType.TIMESTAMP) 
	@Column(name="insertDate")
	private Date insertDate;
	
	@Temporal(TemporalType.TIMESTAMP) 
	@Column(name="updateDate")
	private Date updateDate;
	
}
