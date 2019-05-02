package com.partha.userService.util;

import java.util.Random;

public class CommonUtils {
	
	/**
	 * method to check if a string is null or empty
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s){
		boolean result=false;
		if(s==null){
			result=true;
		}else if(s.isEmpty()){
			result = true;
		}else if(s.trim().length()==0){
			result = true;
		}else{
			result= false;
		}
		return result;
	}
	
	public static String generatePassword(){
		int length = 8;
		System.out.println("Generating password using random() : "); 
        System.out.print("Your new password is : "); 
  
        // A strong password has Cap_chars, Lower_chars, 
        // numeric value and symbols. So we are using all of 
        // them to generate our password 
        String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
        String Small_chars = "abcdefghijklmnopqrstuvwxyz"; 
        String numbers = "0123456789"; 
                String symbols = "!@#$%^&*_=+-/.?<>)"; 
  
  
        String values = Capital_chars + Small_chars + 
                        numbers + symbols; 
  
        // Using random method 
        Random rndm_method = new Random(); 
  
        char[] password = new char[length]; 
  
        for (int i = 0; i < length; i++) 
        { 
            // Use of charAt() method : to get character value 
            // Use of nextInt() as it is scanning the value as int 
            password[i] = 
              values.charAt(rndm_method.nextInt(values.length())); 
  
        } 
        return new String(password); 
	}
	

}


