package com.partha.productService.constants;

public class SQLConstant {
	
	public static final String FIND_CART_ITEMS_BY_USERID =" select c.id cartId , c.userId userId, c.quantity quantity , b.id bookId , b.title  title , b.description , b.author author  , b.publisher publisher ,b.category category  , b.image image "+
			"from Cart c "+
			"left outer join Books b "+
			"on b.id = c.bookId "+
			"where c.userId = :1 ";

}
