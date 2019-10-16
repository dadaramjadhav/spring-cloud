package com.cg.spring.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cg.spring.model.Product;

public interface ProductDaoRepository extends PagingAndSortingRepository<Product, Long> {
	List<Product> findAll();
	
}
