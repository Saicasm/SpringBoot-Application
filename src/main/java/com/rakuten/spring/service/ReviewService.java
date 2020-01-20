package com.rakuten.spring.service;

import java.util.List;

import com.rakuten.spring.domain.Product;
import com.rakuten.spring.domain.Review;

public interface ReviewService {

	int addNewReview(Review toBeAdded,  int productId);

	void removeReview(int id);
	
	  Review findReviewById(int id);


}
