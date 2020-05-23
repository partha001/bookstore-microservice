
package com.partha.userService.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.partha.userService.dto.ForgotPasswordDto;
import com.partha.userService.entities.AddressDetails;
import com.partha.userService.entities.Authority;
import com.partha.userService.entities.GeneratedPassword;
import com.partha.userService.entities.User;
import com.partha.userService.exception.MyException;
import com.partha.userService.repositories.AddressDetailsRepository;
import com.partha.userService.repositories.GeneratedPasswordRepository;
import com.partha.userService.repositories.UserRepository;
import com.partha.userService.request.ChangePasswordRequest;
import com.partha.userService.request.UpdateAddressRequest;
import com.partha.userService.response.AddressDetailsResponse;
import com.partha.userService.response.GenericResponse;
import com.partha.userService.util.CommonUtils;

@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private GeneratedPasswordRepository generatedPasswordRepository;
	
	@Autowired
	private AddressDetailsRepository addressRepository;
	
	
	@Autowired
	private BCryptPasswordEncoder encoder;


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
			throw new MyException("user not found",null,HttpStatus.NOT_FOUND);
		}
	}


	public User findWithUsername(String username) throws MyException  {
		logger.info("UserService.findWithUsername() :: start");
		User foundUser = findByUsername(username);
		if(foundUser!=null){
			foundUser.setAuthorities(foundUser.getAuthorities());
			//foundUser.getAuthorities();
			return foundUser;
		}else{
			throw new MyException("no user found with the given username",null,HttpStatus.NOT_FOUND);
		}
	}
	
	public boolean checkUsernameAvailability(String username){
		logger.info("UserService.checkUsernameAvailability() :: start");
		User foundUser = findByUsername(username);
		if(foundUser!=null){
			return true;
		}else{
			return false;
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
		else if(CommonUtils.isEmpty(user.getSecurityQuestion())){
			errmsg="security question left blank";
		}else if(CommonUtils.isEmpty(user.getSecurityAnswer())){
			errmsg="security answer left blank";
		}
		else if(CommonUtils.isEmpty(user.getPassword())){
			errmsg="not a valid password";
		}
		if(errmsg.equals("")){
			try{
				user.setAccountNonExpired(true);
				user.setAccountNonLocked(true);
				user.setCredentialsNonExpired(true);
				user.setEnabled(true);
				user.setPassword(encoder.encode(user.getPassword()));
				
				List<Authority> authorities = new ArrayList<>();
				Authority authority=new Authority();
				authority.setAuthority("ROLE_USER");
				authority.setUser(user);
			    authorities.add(authority);	
			    user.setAuthorities(authorities);
				
				return userRepository.save(user);
			}catch(Exception ex){
				logger.error("UserService.register() :: error",ex);
				throw new MyException("user registration failed",ex,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}else{
			logger.error("UserService.register() :: error",errmsg);
			throw new MyException(errmsg,null,HttpStatus.BAD_REQUEST);
		}
	}
	
	@Transactional
	public GeneratedPassword generatePassword(String username) throws MyException  {
		logger.info("UserService.generatePassword() :: start");
		try{
			String generatedPassword = CommonUtils.generatePassword();
			User user = findByUsername(username);
			List<GeneratedPassword> generatedPasswords = user.getGeneratedPasswords();
			
			//disabling previously generated passwords
			generatedPasswords.stream()
								.forEach(gp -> gp.setActive(false));
			
			
			GeneratedPassword password = GeneratedPassword.builder()
					.active(true)
					.generatedPassword(generatedPassword)
					.user(user)
					.generationDate(new Date())
					.build();
			
			generatedPasswords.add(password);
			user.setGeneratedPasswords(generatedPasswords);
			user.setPassword(encoder.encode(generatedPassword));
			userRepository.save(user);
			return password;
		}catch(Exception ex){
			logger.error("UserService.generatePassword() :: error",ex);
			throw new MyException("new password generation failed",ex,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@Transactional
	public ForgotPasswordDto forgotPassword(String username) throws MyException  {
		logger.info("UserService.forgotPassword() :: start");
		try{
			User user = findByUsername(username);
			
			if(user==null )
				throw new MyException("this is not a registered email address",null , HttpStatus.NOT_FOUND);
			
			ForgotPasswordDto dto = ForgotPasswordDto.builder()
					.email(user.getUsername())
					.securityQuestion(user.getSecurityQuestion())
					.securityAnswer(user.getSecurityAnswer())
					.build();		
			return dto;		
		}
		catch(MyException ex){
			throw ex;
		}
		catch(Exception ex){
			throw new MyException("",ex,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	public ResponseEntity<GenericResponse> changePassword(ChangePasswordRequest request) {
		logger.info("UserService.changePassword() :: start");
		GenericResponse response = null;
		Optional<User> optional = this.userRepository.findById(request.getId());
		if(optional.isPresent()) {
			User user = optional.get();
			user.setPassword(encoder.encode(request.getNewPassword()));
			userRepository.save(user);
			response = response.builder()
				.messge("password change successful")
				.build();
		}
		return new ResponseEntity<GenericResponse>(response, HttpStatus.OK);
	}

	public ResponseEntity<GenericResponse> updateAddress(UpdateAddressRequest request) {
		logger.info("UserService.updateAddress() :: start");
		GenericResponse response = null;
		Optional<User> optional = this.userRepository.findById(request.getUserId());
		if(optional.isPresent()) {		
			User user = optional.get();
			AddressDetails oldAddress = addressRepository.findByUser(user);		
			AddressDetails newAddress = new AddressDetails();
			if(oldAddress!=null) {
				newAddress.setId(oldAddress.getId());
				newAddress.setInsertDate(oldAddress.getInsertDate());
				newAddress.setUpdateDate(new Date());
			}else {
				newAddress.setInsertDate(new Date());
			}
			newAddress.setAddressLine1(request.getAddressLine1());
			newAddress.setAddressLine2(request.getAddressLine2());
			newAddress.setPincode(request.getPincode());
			newAddress.setState(request.getState());
			newAddress.setCountry(request.getCountry());
			newAddress.setUser(user);	
			addressRepository.save(newAddress);
			response = response.builder()
				.messge("address updated successfully")
				.build();
		}
		return new ResponseEntity<GenericResponse>(response, HttpStatus.OK);
	
	}

	public ResponseEntity<AddressDetailsResponse> getAddressDetails(Integer userId) {
		AddressDetails address = addressRepository.findByUser(User.builder().id(userId).build());
		AddressDetailsResponse response = null;
		if(address!=null) {
			response = AddressDetailsResponse.builder()
				.userId(userId)
				.addressId(address.getId())
				.addressLine1(address.getAddressLine1())
				.addressLine2(address.getAddressLine2())
				.pincode(address.getPincode())
				.state(address.getState())
				.country(address.getCountry())
				.build();
			return new ResponseEntity<AddressDetailsResponse>(response,HttpStatus.OK);
		}
		return new ResponseEntity<AddressDetailsResponse>(response,HttpStatus.NO_CONTENT);
		
	}

	

}









