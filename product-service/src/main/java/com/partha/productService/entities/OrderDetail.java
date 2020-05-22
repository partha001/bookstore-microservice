package com.partha.productService.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name="OrderDetail")
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	@Column(name="orderId")
	private Integer orderId;
	
	@Column(name="bookId")
	private Integer bookId;
	
	@Column(name="quantity")
	private Integer quantity;
	
	@Temporal(TemporalType.TIMESTAMP) 
	@Column(name="insertDate")
	private Date insertDate;
	
	@Temporal(TemporalType.TIMESTAMP) 
	@Column(name="updateDate")
	private Date updateDate;

}
