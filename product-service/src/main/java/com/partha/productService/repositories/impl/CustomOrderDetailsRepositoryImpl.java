package com.partha.productService.repositories.impl;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.partha.productService.constants.AppConstants;
import com.partha.productService.dto.OrderHistoryDto;
import com.partha.productService.repositories.CustomOrderDetailsRepository;
import com.partha.productService.util.CommonUtils;

public class CustomOrderDetailsRepositoryImpl implements CustomOrderDetailsRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate njdbcTemplate;

	@Override
	public List<OrderHistoryDto> getOrderHistory(int userId) {
		String sql=" SELECT                                      "+
				"    OM.ID AS ORDERID,                        "+
				"    OM.DELIVERYADDRESS AS DELIVERYADDRESS,   "+
				"    OM.INSERTDATE AS ORDERDATE,              "+
				"    OD.ID AS ORDERDETAILID,                  "+
				"    OD.BOOKID AS BOOKID,                     "+
				"    OD.QUANTITY AS QUANTITY,                 "+
				"    BOOK.TITLE AS TITLE,                     "+
				"    BOOK.AUTHOR AS AUTHOR,                   "+
				"    BOOK.PRICE AS PRICE,                     "+
				"    BOOK.IMAGE AS IMAGE,                     "+
				"    BOOK.DESCRIPTION AS DESCRIPTION          "+
				" FROM ORDERMASTER OM                         "+
				" LEFT OUTER JOIN ORDERDETAIL OD              "+
				" ON OM.ID = OD.ORDERID                       "+
				" LEFT OUTER JOIN BOOKS BOOK                  "+
				" ON OD.BOOKID = BOOK.ID                      "+
				" WHERE OM.USERID = :userid                   "+
				" AND OM.ACTIVE = TRUE                        "+
				" ORDER BY OM.INSERTDATE DESC                 ";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("userid", userId);
		 List<OrderHistoryDto> list = njdbcTemplate.query(sql, params, new RowMapper<OrderHistoryDto>() {

			@Override
			public OrderHistoryDto mapRow(ResultSet rs, int rowNum) throws SQLException {
				return OrderHistoryDto.builder()
						.orderId(rs.getInt("ORDERID"))
						.deliveryAddress(rs.getString("DELIVERYADDRESS"))
						.orderDate(CommonUtils.convertTimestampToFormattedString(rs.getTimestamp("ORDERDATE"), AppConstants.DATEFORMAT1) )
						.orderDetailsId(rs.getInt("ORDERDETAILID"))
						.bookid(rs.getInt("BOOKID"))
						.title(rs.getString("TITLE"))
						.quantity(rs.getInt("QUANTITY"))
						.author(rs.getString("AUTHOR"))
						.description(rs.getString("DESCRIPTION"))
						.image(CommonUtils.blobToBase64ImageString(rs.getBlob(("IMAGE"))))
						.price(rs.getDouble("PRICE"))
						.build();
			}
		});
		return list;
	}

}
