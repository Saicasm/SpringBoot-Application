package com.rakuten.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rakuten.spring.dal.ProductDAO;
import com.rakuten.spring.domain.Product;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

  ProductDAO dao; // s= = new ProductDAOInMemImpl();

  @Autowired
  public void setDao(ProductDAO dao) {
    this.dao = dao;
  }

  @Override
  public int addNewProduct(Product toBeAdded) {

    if (toBeAdded.getPrice() * toBeAdded.getQty() >= 10000) {
      Product added = dao.save(toBeAdded);
      return added.getId();
    } else {
      throw new IllegalArgumentException("The monetory va;ue is less than 10000 \n");
    }
  }

  @Override
  public void removeProduct(int id) {
    Product exisiting = dao.finById(id);
    if (exisiting != null) {
      if (exisiting.getPrice() * exisiting.getQty() >= 100000)
        throw new IllegalStateException("Monetory value is greater than 10000");
      else dao.deleteById(id);
    } 
    else 
    	throw new NullPointerException("Null value ");
  }

  @Override
  public List<Product> finadAll() {

    return dao.findAll();
  }

  @Override
  public Product findById(int id) {
    return dao.finById(id);
  }
}
