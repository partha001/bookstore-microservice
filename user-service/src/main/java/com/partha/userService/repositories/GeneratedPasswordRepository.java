package com.partha.userService.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.partha.userService.entities.GeneratedPassword;
import com.partha.userService.entities.User;

@Repository
public interface GeneratedPasswordRepository extends CrudRepository<GeneratedPassword, Integer> , CustomGeneratedPasswordRepository{
	

}
