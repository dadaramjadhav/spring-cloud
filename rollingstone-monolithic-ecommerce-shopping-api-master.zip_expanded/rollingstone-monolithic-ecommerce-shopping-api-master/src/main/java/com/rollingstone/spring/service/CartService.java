package com.rollingstone.spring.service;

import java.util.List;

import com.rollingstone.spring.model.Cart;

public interface CartService {

   long save(Cart category);
   Cart get(long id);
   List<Cart> list();
   void update(long id, Cart category);
   void delete(long id);
}
