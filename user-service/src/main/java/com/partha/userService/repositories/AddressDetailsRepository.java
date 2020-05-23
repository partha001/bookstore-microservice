package com.partha.userService.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.partha.userService.entities.AddressDetails;
import com.partha.userService.entities.User;

@Repository
public interface AddressDetailsRepository extends CrudRepository<AddressDetails, Integer>{

	public AddressDetails findByUser(User user);
}
