package com.rakuten.spring.dal;

import com.rakuten.spring.domain.Product;
import java.util.*;

public interface ProductDAO {

  Product save(Product toBeSaved);

  Product finById(int id);

  List<Product> findAll();

  void deleteById(int id);
}
