package com.partha.userService.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.partha.userService.entities.User;
import com.partha.userService.exception.MyException;
import com.partha.userService.repositories.UserRepository;

@Service
public class UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;

	public  User findById(int id){
		Optional<User> foundUser = userRepository.findById(id) ;
		return (foundUser.isPresent()? foundUser.get() : null) ;
	}
		
	public User findByUsername(String username){
		return userRepository.findByUsername(username) ;
	}
	
	public User login(User user) throws MyException{
		logger.info("UserService.login() :: start");
		User foundUser=findByUsername(user.getUsername());
		if(null!=foundUser){
			//checking username and password
			if(user.getUsername().equals(foundUser.getUsername()) && user.getPassword().equals(foundUser.getPassword())){
				
			}else{
				logger.error("UserService.login() :: password is incorrent");
				throw new MyException(HttpStatus.BAD_REQUEST, "password is incorrent", new RuntimeException());
			}
		}else{
			logger.error("UserService.login() :: user not found");
			throw new MyException(HttpStatus.BAD_REQUEST, "user not found", new RuntimeException());
		}
			
		logger.info("UserService.login() :: end");
		return foundUser;
	}

	public String generateToken(User foundUser) {
		logger.info("UserService.generateToken() :: start");
		
		logger.info("UserService.generateToken() :: end");
		return foundUser.getUsername();
	}
	

}
