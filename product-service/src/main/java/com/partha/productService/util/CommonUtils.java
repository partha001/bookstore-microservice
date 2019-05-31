package com.partha.productService.util;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

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
	
	public static String blobToBase64ImageString(Blob blob){
		
		String result = null;
		int blobLength;
		try {
			blobLength = (int) blob.length();
			byte[] blobAsBytes = blob.getBytes(1, blobLength);
			result = Base64.getEncoder().encodeToString(blobAsBytes);
			} catch (SQLException e) {
			e.printStackTrace();
		} 	
		return result;
	}
	

}

//test comment2
//test comment1
//test comment4 : done from desktop
//test comment3 : done from laptop
