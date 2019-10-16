package com.cg.spring.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cg.spring.model.Category;
import com.cg.spring.model.Product;

public interface CategoryDaoRepository extends PagingAndSortingRepository<Category, Long> {
	List<Category> findAll();
	
}
