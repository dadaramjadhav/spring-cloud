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

import com.cg.spring.events.ProductEvent;
import com.cg.spring.model.Product;
import com.cg.spring.service.ProductService;


@RestController
public class ProductController extends AbstractController{

	@Autowired
	private ProductService  productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}


	/*---Add new Product---*/
	@PostMapping("/product")
	public ResponseEntity<?> createProduct(@RequestBody Product product) {
		Product savedProduct = productService.save(product);
		ProductEvent productCreatedEvent = new ProductEvent("one product created", savedProduct);
		eventPublisher.publishEvent(productCreatedEvent);
		return ResponseEntity.ok().body("New Product has been saved with ID:" + savedProduct.getId());
	}

	/*---Get a Product by id---*/
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") long id) {
		Optional<Product> returnedProduct = productService.get(id);
		Product product = returnedProduct.get();
		ProductEvent productCreatedEvent = new ProductEvent("one product is retrieved", product);
		eventPublisher.publishEvent(productCreatedEvent);
		return ResponseEntity.ok().body(product);
	}

	/*---get all Categories---*/
	@GetMapping("/product")
	public Page<Product> getProductsByPage(
			@RequestParam(value = "pagenumber", required = true, defaultValue = "0") Integer pageNumber,
			@RequestParam(value = "pagesize", defaultValue = "20") Integer pageSize
			) {
		Page<Product> pagedProducts = productService.getProductByPage(pageNumber, pageSize);
		return pagedProducts;
	}

	/*---Update a Product by id---*/
	@PutMapping("/product/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
		checkResourceFound(this.productService.get(id));
		productService.update(id, product);
		return ResponseEntity.ok().body("Product has been updated successfully.");
	}

	/*---Delete a Product by id---*/
	@DeleteMapping("/product/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") long id) {
		checkResourceFound(this.productService.get(id));
		productService.delete(id);
		return ResponseEntity.ok().body("Product has been deleted successfully.");
	}
}