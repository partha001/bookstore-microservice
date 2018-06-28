package com.partha.userService.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.partha.userService.entities.User;
import com.partha.userService.exception.MyException;
import com.partha.userService.repositories.UserRepository;
import com.partha.userService.util.CommonUtils;

@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;


	//reusable methods which correspond to repository start
	public  User findById(int id){
		return userRepository.findById(id).orElse(null) ;
	}

	public User findByUsername(String username){
		return userRepository.findByUsername(username);
	}
	//reusable methods which correspond to repository end


	//method which correspond to controllers start
	public User findWithId(int id) throws MyException {
		logger.info("UserService.findWithId() :: start");
		User foundUser = findById(id);
		if(foundUser!=null){
			return foundUser;
		}else{
			throw new MyException(HttpStatus.NOT_FOUND, "no user found with the given id", new RuntimeException());
		}
	}


	public User findWithUsername(String username) throws MyException  {
		logger.info("UserService.findWithUsername() :: start");
		User foundUser = findByUsername(username);
		if(foundUser!=null){
			return foundUser;
		}else{
			throw new MyException(HttpStatus.NOT_FOUND, "no user found with the given username", new RuntimeException());
		}
	}


	@Transactional
	public User register(User user) throws MyException  {
		logger.info("UserService.register() :: start");
		String errmsg="";
		if(CommonUtils.isEmpty(user.getUsername())){
			errmsg="not a valid username";
		}
		else if(findByUsername(user.getUsername())!=null){
			errmsg="username already exists";
		}
		else if(CommonUtils.isEmpty(user.getPassword())){
			errmsg="not a valid password";
		}
		if(errmsg.equals("")){
			try{					
				return userRepository.save(user);
			}catch(Exception ex){
				throw new MyException(HttpStatus.INTERNAL_SERVER_ERROR, "user registration failed", new RuntimeException());
			}

		}else{
			throw new MyException(HttpStatus.BAD_REQUEST, errmsg, new RuntimeException());
		}
	}
	
	//method which correspond to controllers end
	

}









