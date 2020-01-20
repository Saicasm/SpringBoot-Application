package com.rakuten.spring.dal;

import java.util.List;

import com.rakuten.spring.domain.Review;

public interface ReviewDAO {

  Review findById(int id);

  Review save(Review toBeSaved);

  void deleteById(int id);

  List<Review> finadAll();
}