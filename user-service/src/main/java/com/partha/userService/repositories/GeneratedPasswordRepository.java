package com.partha.userService.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.partha.userService.entities.GeneratedPassword;

@Repository
public interface GeneratedPasswordRepository extends CrudRepository<GeneratedPassword, Integer> , CustomGeneratedPasswordRepository{
	

}
