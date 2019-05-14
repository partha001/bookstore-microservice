package com.partha.databaseService.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name="books")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="author")
	private String author;
	
	@Column(name="category")
	private String category;
	
	@Column(name="availableUnits")
	private Integer availableUnits;
	
	@Column(name="price")
	private BigDecimal price;
	
	@Column(name="active")
	private Boolean active;
		
	@Temporal(TemporalType.TIMESTAMP) 
	@Column(name="insertDate")
	private Date insertDate;
	
	@Temporal(TemporalType.TIMESTAMP) 
	@Column(name="updateDate")
	private Date updateDate;
	
	
	@Column(name="publisher")
	private String publisher;
	
	@Column(name="language")
	private String language;
	
	@Column(name="pages")
	private Integer pages;
	
	@Column(name="isbn")
	private String isbn;
	
	@Column(name="format")
	private String format;
	
	@Column(name="description")
	private String description;
	
	@Temporal(TemporalType.TIMESTAMP) 
	@Column(name="publicationDate")
	private Date publicationDate;
	
	@Lob
	@Column(name="image",columnDefinition="mediumblob")
	private byte[] image;
	
	
}
