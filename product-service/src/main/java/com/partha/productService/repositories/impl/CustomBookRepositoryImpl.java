package com.partha.productService.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.partha.productService.entities.Book;
import com.partha.productService.repositories.CustomBookRepository;

public class CustomBookRepositoryImpl implements CustomBookRepository{

	@Autowired
	private EntityManager em;

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
}
