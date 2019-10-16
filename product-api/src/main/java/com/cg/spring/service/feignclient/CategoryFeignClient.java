package com.cg.spring.service.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cg.spring.model.Category;

@FeignClient(name="category-api")
public interface CategoryFeignClient {

	@GetMapping("/category/{id}")
	public Category getCategory(@PathVariable("id") long id);
}
