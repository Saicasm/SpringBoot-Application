package com.rakuten.spring.dal;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rakuten.spring.domain.Product;
import com.rakuten.spring.domain.Review;

@Transactional
@Repository
public class ReviewDAOJpaImpl implements ReviewDAO {
	
	@Autowired
	EntityManager em;
	@Override
  public Review findById(int id) {
		
		return em.find(Review.class, id);
	}
	@Override
  public Review save(Review toBeSaved)
	{
		em.persist(toBeSaved);
		return toBeSaved;
	}
	@Override
  public void deleteById(int id)
	{
		Review r=em.find(Review.class, id);
		em.remove(r);
	}
	@Override
  public List<Review> finadAll(){
		return null;
	}
}
