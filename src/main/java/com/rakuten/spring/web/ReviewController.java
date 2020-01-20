package com.rakuten.spring.web;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rakuten.spring.domain.Product;
import com.rakuten.spring.domain.Review;
import com.rakuten.spring.service.ProductService;
import com.rakuten.spring.service.ReviewService;

@RestController
public class ReviewController {
	
	@Autowired // this enables loose coupling and allows us to access the impl objects
	  ProductService service;
	@Autowired 
	ReviewService reservice;
	@GetMapping("/products/{id}/review")
	  public ResponseEntity<List<Review>> getRviewById(@PathVariable("id") int id) {
	    Product p = service.findById(id);
	    if (p != null) {
	      List<Review> rev = p.getReviews();
	      return new ResponseEntity<List<Review>>(rev, HttpStatus.OK);
	    }
	    return new ResponseEntity<List<Review>>(HttpStatus.NOT_FOUND);
	  }

	  @PostMapping("products/{id}/review")
	  public ResponseEntity<Review> addNewReview(
	      @PathVariable("id") int id, @RequestBody Review toBeAdded) {
	    try {
	      int rid = reservice.addNewReview(toBeAdded, id);
	      HttpHeaders headers = new HttpHeaders();
	      headers.setLocation(URI.create("/products/" + id + "review" + rid));
	      return new ResponseEntity<Review>(headers, HttpStatus.CREATED);
	    } catch (IllegalArgumentException e) {
	      return new ResponseEntity<Review>(HttpStatus.CONFLICT);
	    }
	  }
}
