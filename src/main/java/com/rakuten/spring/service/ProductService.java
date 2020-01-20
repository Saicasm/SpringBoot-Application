package com.rakuten.spring.service;

import java.util.List;

import com.rakuten.spring.domain.Product;

public interface ProductService {

  int addNewProduct(Product toBeAdded);

  void removeProduct(int id);

  List<Product> finadAll();

  Product findById(int id);
}
