package com.partha.productService.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.partha.productService.constants.SQLConstant;
import com.partha.productService.dto.CartItemsDto;
import com.partha.productService.entities.CartItem;

@Repository
public interface CartRepository extends CrudRepository<CartItem, Integer>, CustomCartRepository {

	
	@Query( value = "SELECT * FROM CART C WHERE C.BOOKID=?1 AND C.USERID=?2", 
			nativeQuery = true)
	public CartItem findCartItemByBookIdAndUserId(Integer bookid, Integer userid);
	
//	@Query(value=SQLConstant.FIND_CART_ITEMS_BY_USERID,nativeQuery=true)
	public List<CartItemsDto> findByUserId(@Param("userid") Integer userId);
	
}
