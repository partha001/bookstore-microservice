package com.partha.productService.util;

import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.google.common.base.Strings;

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
	
	public static Timestamp convertStringToTimestamp(String s,String inputPattern) {
		if(!Strings.isNullOrEmpty(s)) {
			LocalDateTime inputDate = null;
			DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(inputPattern, Locale.ENGLISH);
			inputDate = LocalDateTime.parse(s, inputFormatter);
			return inputDate == null ? null : Timestamp.valueOf(inputDate);
		}else {
			return null;
		}
	}
	
	public static String convertTimestampToFormattedString(java.sql.Timestamp timestamp,String format) {
		if(!Strings.isNullOrEmpty(format) && timestamp!=null) {
			Date date = new Date(timestamp.getTime());
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}else {
			return null;
		}
	}


	public static java.sql.Date convertStrignToSQLDate(String input,String inputPattern) throws ParseException {
		if(!Strings.isNullOrEmpty(input)) {
			java.util.Date udate=new SimpleDateFormat(inputPattern).parse(input);  
			return new java.sql.Date(udate.getTime());
		}
		else {
			return null;
		}
	}


	public static Date convertLocalDateToSQLDate(LocalDate localDate) {
		return localDate == null ? null : Date.valueOf(localDate);
	}


	public static java.sql.Date convertUtilDateToSqlDate(java.util.Date uDate){
		java.sql.Date sqlDate = new java.sql.Date(uDate.getTime());
		return sqlDate;
	}

	public static String convertDateToString(java.util.Date date, String format) {
		if(date==null || Strings.isNullOrEmpty(format)) {
			return null;
		}else {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}
	}

	public static java.util.Date convertStringToDate(String input,String format) throws ParseException {
		if(Strings.isNullOrEmpty(input) || Strings.isNullOrEmpty(format)) {
			return null;
		}else {
			SimpleDateFormat sdf=new SimpleDateFormat(format);
			sdf.setLenient(false);
			return sdf.parse(input);
		}
	}

	public static java.util.Date getFutureTime(java.util.Date initialTime, long millisToAdd){
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTimeInMillis(initialTime.getTime()+ millisToAdd);
		return cal.getTime();
	}


	public static java.util.Date getPastTime(java.util.Date initialTime, long millisToSubtract){
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTimeInMillis(initialTime.getTime()- millisToSubtract);
		return cal.getTime();
	}

	

}

