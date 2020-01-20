package com.rakuten.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rakuten.spring.dal.ProductDAO;
import com.rakuten.spring.dal.ReviewDAO;
import com.rakuten.spring.domain.Product;
import com.rakuten.spring.domain.Review;

@Service
@Transactional
public class ReviewServiceJpaImpl implements ReviewService {

	ReviewDAO dao;// = new ReviewDAOInMemImpl();
	ProductDAO pdao;
	
	@Autowired
	public void setDao(ReviewDAO dao) {
		this.dao = dao;
	}
	
	@Autowired
	public void setPdao(ProductDAO pdao) {
    this.pdao = pdao;
  }
	// Failure should be returned as Exception. Absence of an object is returned as
	// empty optional
	@Override
	public int addNewReview(Review toBeAdded, int productId) {
		Product exisiting =pdao.finById(productId);
		if(exisiting!= null)
		{
			toBeAdded.setProduct(exisiting);
			Review added =dao.save(toBeAdded);
			return added.getId();
		}
		else
			throw new NoSuchProductException();
		
	}

	@Override
	public void removeReview(int id) {
		Review existing = dao.findById(id);
		if (existing != null) {
			dao.deleteById(id);
		}
		else {
			throw new NullPointerException("Review does not exist");
		}
	}

  @Override
  public Review findReviewById(int id) {
	  Review r=dao.findById(id);
	  return r;
  }
  }

	

