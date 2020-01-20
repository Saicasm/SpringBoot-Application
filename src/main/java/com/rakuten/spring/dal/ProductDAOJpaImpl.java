package com.rakuten.spring.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rakuten.spring.domain.Product;

@Repository
@Transactional
public class ProductDAOJpaImpl implements ProductDAO {

  @Autowired EntityManager em;

  @Override
  public Product save(Product toBeSaved) {
    em.persist(toBeSaved); // This is equal to insert
    return toBeSaved;
  }

  @Override
  public Product finById(int id) {
    return em.find(Product.class, id);
  }

  @Override
  public List<Product> findAll() {
	  Query q =em.createQuery("select p from Product as p ");
	  List<Product> all =q.getResultList();
    return all;
  }

  @Override
  public void deleteById(int id) {
	  Product p=em.find(Product.class, id);
    em.remove(p);	
    /* Query q =em.createQuery("delete  from Product as p where p.id=:idParam");
    q.setParameter("idParam", id);
    q.executeUpdate();*/

  }
}
