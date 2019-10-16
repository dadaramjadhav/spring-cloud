package com.cg.spring.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.spring.events.CategoryEvent;
import com.cg.spring.model.Category;
import com.cg.spring.model.Product;
import com.cg.spring.service.CategoryService;


@RestController
public class CategoryController extends AbstractController{

	@Autowired
	private CategoryService  categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}


	/*---Add new Product---*/
	@PostMapping("/category")
	public ResponseEntity<?> createProduct(@RequestBody Category category) {
		Category savedCategory = categoryService.save(category);
		CategoryEvent categoryCreatedEvent = new CategoryEvent("one category created", savedCategory);
		eventPublisher.publishEvent(categoryCreatedEvent);
		return ResponseEntity.ok().body("New Product has been saved with ID:" + savedCategory.getId());
	}

	/*---Get a Product by id---*/
	@GetMapping("/category/{id}")
	public ResponseEntity<Category> getCategory(@PathVariable("id") long id) {
		Optional<Category> returnedCategory = categoryService.get(id);
		Category category = returnedCategory.get();
		CategoryEvent categoryCreatedEvent = new CategoryEvent("one product is retrieved", category);
		eventPublisher.publishEvent(categoryCreatedEvent);
		return ResponseEntity.ok().body(category);
	}

	/*---get all Categories---*/
	@GetMapping("/category")
	public Page<Category> getCategoriesByPage(
			@RequestParam(value = "pagenumber", required = true, defaultValue = "0") Integer pageNumber,
			@RequestParam(value = "pagesize", defaultValue = "20") Integer pageSize
			) {
		Page<Category> pagedCategorys = categoryService.getCategoriesByPage(pageNumber, pageSize);
		return pagedCategorys;
	}

	/*---Update a Product by id---*/
	@PutMapping("/category/{id}")
	public ResponseEntity<?> updateCategory(@PathVariable("id") long id, @RequestBody Category category) {
		checkResourceFound(this.categoryService.get(id));
		categoryService.update(id, category);
		return ResponseEntity.ok().body("Category has been updated successfully.");
	}

	/*---Delete a Product by id---*/
	@DeleteMapping("/category/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable("id") long id) {
		checkResourceFound(this.categoryService.get(id));
		categoryService.delete(id);
		return ResponseEntity.ok().body("Category has been deleted successfully.");
	}
}