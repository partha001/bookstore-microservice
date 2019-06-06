package com.partha.productService.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.partha.productService.entities.CartItem;

@Repository
public interface CartRepository extends CrudRepository<CartItem, Integer>, CustomCartRepository {

	
	@Query( value = "SELECT * FROM CART C WHERE C.BOOKID=?1 AND C.USERID=?2", 
			nativeQuery = true)
	public CartItem findCartItemByBookIdAndUserId(Integer bookid, Integer userid);
	
	
}
