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

import com.cg.spring.dao.ProductDaoRepository;
import com.cg.spring.exceptions.HTTP400Exception;
import com.cg.spring.model.Category;
import com.cg.spring.model.Product;
import com.cg.spring.service.feignclient.CategoryFeignClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;



@Service
public class ProductServiceImpl implements ProductService {

	final static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductDaoRepository productDaoRepository;

	@Autowired
	CategoryFeignClient categoryClient;
	
	@Override
	@HystrixCommand(fallbackMethod = "saveProductWithoutValidation")
	public Product save(Product product) {

		Category category = null;
		Category parentCategory = null;
		
		if (product.getCategory() == null) {
			logger.info("Product Category is null :");
		}else {
			logger.info("Product Category is not null :"+product.getCategory());
			logger.info("Product Category is not null ID :"+product.getCategory().getId());
			
			try {
				category = categoryClient.getCategory(product.getCategory().getId());
			}catch(Exception ex) {
				logger.info("product category does not exists" + product.getCategory().getId());
				throw new HTTP400Exception("Bad request as category provided is not valid");
			}

		}

		if (product.getParentCategory() == null) {
			logger.info("Product Parent Category is null :");
		}else {
			logger.info("Product Parent Category is not null :"+product.getParentCategory());
			logger.info("Product Parent Category is not null Id :"+product.getParentCategory().getId());

			try {
				category = categoryClient.getCategory(product.getParentCategory().getId());
			}catch(Exception ex) {
				logger.info("product category does not exists" + product.getParentCategory().getId());
				throw new HTTP400Exception("Bad request as category provided is not valid(parent)");
			}
		}
		return (Product) productDaoRepository.save(product);
	}
	
	public Product saveProductWithoutValidation(Product product) {
		logger.info("hystrix circuit breaker enabled and called fallback method ");
		return (Product) productDaoRepository.save(product);
	}

	@Override
	public Optional<Product> get(long id) {
		return productDaoRepository.findById(id);
	}

	@Override
	public Page<Product> getProductByPage(Integer pageNumber, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("productCode").descending());
		return productDaoRepository.findAll(pageable);
	}

	@Override
	public void update(long id, Product product) {
		productDaoRepository.save(product);
	}

	@Override
	public void delete(long id) {
		productDaoRepository.deleteById(id);
	}

}
