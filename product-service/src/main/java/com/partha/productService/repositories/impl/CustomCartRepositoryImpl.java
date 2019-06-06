package com.partha.productService.repositories.impl;

import java.sql.Blob;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.partha.productService.constants.SQLConstant;
import com.partha.productService.dto.CartItemsDto;
import com.partha.productService.repositories.CustomCartRepository;
import com.partha.productService.util.CommonUtils;

public class CustomCartRepositoryImpl implements CustomCartRepository{

	@Autowired
	private EntityManager em;

	
	public List<CartItemsDto> findByUserId(Integer userId) {
		String sql = String.format(SQLConstant.FIND_CART_ITEMS_BY_USERID, userId);
		javax.persistence.Query nativeQuery = em.createNativeQuery(sql)
				.setParameter(1, userId);
		List<Object> resultList = nativeQuery.getResultList();
		List<CartItemsDto> result = resultList.stream().map( o -> {
			Object[] row = (Object[])o;
			return CartItemsDto.builder()
			.cartId((Integer)row[0])
			.userId((Integer)row[1])
			.quantity((Integer)row[2])
			.bookId((Integer)row[3])
			.title((String)row[4])
			.description((String)row[5])
			.author((String)row[6])
			.publisher((String)row[7])
			.category((String)row[8])
			.image(CommonUtils.blobToBase64ImageString((Blob)row[9]))
			//.image((Blob))
			//.image(Base64.getEncoder().encodeToString(((row[9])).getBytes())
			//.image((String)row[9]))
			.build();
		}).collect(Collectors.toList());
		
		return result;
	}
}
	



