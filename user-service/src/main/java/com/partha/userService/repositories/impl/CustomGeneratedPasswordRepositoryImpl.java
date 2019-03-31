package com.partha.userService.repositories.impl;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.partha.userService.repositories.CustomGeneratedPasswordRepository;

public class CustomGeneratedPasswordRepositoryImpl implements CustomGeneratedPasswordRepository{
	
	@Autowired
	private EntityManager em;
	
//	 @Modifying
//	 @Query(value = "update generatedPasswords set active=0 where ", name="query1",nativeQuery = false)
//	 public void deactivatePreviousGeneratedPasswords(int id){
//		  Query createNamedQuery = em.createNativeQuery("query1", arg1)
//		 
//		 
//	 }

}
