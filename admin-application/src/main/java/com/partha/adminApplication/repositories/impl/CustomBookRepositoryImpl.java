package com.partha.adminApplication.repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.partha.adminApplication.constants.SQLConstant;
import com.partha.adminApplication.dto.MonthlySalesReport;
import com.partha.adminApplication.entities.Book;
import com.partha.adminApplication.repositories.CustomBookRepository;

public class CustomBookRepositoryImpl implements CustomBookRepository{

	@Autowired
	private EntityManager em;

	@Autowired
	NamedParameterJdbcTemplate nJdbcTemplate;

	@Override
	public List<Book> searchBooks(int itemsPerPage, int pageNo) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Book> cq = cb.createQuery(Book.class); 
		Root<Book> root = cq.from(Book.class);
		Predicate activeBookFilter  = cb.equal(root.get("active"), true);
		// Predicate titlePredicate = cb.like(book.get("title"), "%" + title + "%");
		cq.where(activeBookFilter);

		TypedQuery<Book> query = em.createQuery(cq);

		int firstIndex=(pageNo-1)*itemsPerPage;
		query.setFirstResult(firstIndex);
		query.setMaxResults(itemsPerPage);
		List<Book> booklist = query.getResultList();

		return booklist;
	}

	@Override
	public int searchBooksCount(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Book> cq = cb.createQuery(Book.class); 
		Root<Book> root = cq.from(Book.class);
		Predicate activeBookFilter  = cb.equal(root.get("active"), true);
		// Predicate titlePredicate = cb.like(book.get("title"), "%" + title + "%");
		cq.where(activeBookFilter);

		TypedQuery<Book> query = em.createQuery(cq);
		return query.getResultList().size();
	}

	@Override
	public List<Book> getInventoryExportDetails() {
		List<Book> books = nJdbcTemplate.query(SQLConstant.INVENTORY_EXPORT_QUERY, new RowMapper<Book>() {

			@Override
			public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
				return Book.builder()
						.id(rs.getInt("id"))
						.title(rs.getString("title"))
						.author(rs.getString("author"))
						.publisher(rs.getString("publisher"))
						.availableUnits(rs.getInt("availableunits"))
						.build();
			}
		});
		return books;

	}

	@Override
	public List<MonthlySalesReport> getMonthlySalesReport(int year) {
		MapSqlParameterSource param=new MapSqlParameterSource();
		List<MonthlySalesReport> list = new ArrayList<>();
		param.addValue("year", year);
		list = nJdbcTemplate.query(SQLConstant.MONTHLY_SALES_REPORT_QUERY, param, new RowMapper<MonthlySalesReport>() {

			@Override
			public MonthlySalesReport mapRow(ResultSet rs, int rowNum) throws SQLException {
				return MonthlySalesReport.builder()
						.monthNo(rs.getInt("MNO"))
						.monthName(rs.getString("MNAME"))
						.saleCount(rs.getInt("RECORDCOUNT"))
						.build();
			}
		});
		return list;
	}



}
