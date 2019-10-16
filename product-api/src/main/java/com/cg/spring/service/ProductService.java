package com.cg.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.cg.spring.model.Product;

 
public interface ProductService {

   Product save(Product product);
   Optional<Product> get(long id);
   Page<Product> getProductByPage(Integer pageNumber, Integer pageSize);
   void update(long id, Product product);
   void delete(long id);
}
