package com.partha.userService.util;

public class CommonUtils {
	
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
	

}

//test comment2
//test comment1
//test comment3 : done from laptop
