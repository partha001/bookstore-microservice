package com.partha.productService.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

//@RunWith(PowerMockRunner.class)
public class CommonUtilsTest {
	
	@Test
	public void isEmptyShouldReturnCorrectValue(){
		boolean result;
		result=CommonUtils.isEmpty("");
		assertTrue(result);
		
		result=CommonUtils.isEmpty("  ");
		assertTrue(result);
		
		result=CommonUtils.isEmpty("123");
		assertFalse(result);
	}

}
