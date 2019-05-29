package com.partha.productService.repositories.impl;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.partha.productService.repositories.CustomCartRepository;

public class CustomCartRepositoryImpl implements CustomCartRepository{

	@Autowired
	private EntityManager em;
	


}
