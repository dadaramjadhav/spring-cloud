package com.cg.spring.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cg.spring.dao.CategoryDaoRepository;
import com.cg.spring.model.Category;
import com.cg.spring.model.Product;



@Service
public class CategoryServiceImpl implements CategoryService {

	final static Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Autowired
	private CategoryDaoRepository categoryDaoRepository;

	@Override
	public Category save(Category category) {
		return (Category) categoryDaoRepository.save(category);
	}

	@Override
	public Optional<Category> get(long id) {
		return categoryDaoRepository.findById(id);
	}

	@Override
	public Page<Category> getCategoriesByPage(Integer pageNumber, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());
		return categoryDaoRepository.findAll(pageable);
	}

	@Override
	public void update(long id, Category category) {
		categoryDaoRepository.save(category);
	}

	@Override
	public void delete(long id) {
		categoryDaoRepository.deleteById(id);
	}
}