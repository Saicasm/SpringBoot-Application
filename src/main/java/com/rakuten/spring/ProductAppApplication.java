package com.rakuten.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.rakuten.spring.dal.ProductDAO;
import com.rakuten.spring.dal.ReviewDAO;
import com.rakuten.spring.domain.Product;
import com.rakuten.spring.domain.Review;

import java.util.*;

@SpringBootApplication
public class ProductAppApplication {

  public static void main(String[] args) {
    /*    ApplicationContext springContainer =
     */ SpringApplication.run(
        ProductAppApplication.class, args); // Builds application context automatically
    /* //		ProductConsoleUi ui =springContainer.getBean(ProductConsoleUi.class);
    //		//ui.createproductWithUI();
    //		ui.findProductById();
    // ui.deleteById();
     ReviewDAO reviewDAO=springContainer.getBean(ReviewDAO.class);
         Review sample =new Review("Its working ","IDK ",3);
        Review saved= reviewDAO.save(sample,3);
        System.out.println("Created review with id " +saved.getId() );
          ProductDAO pdao = springContainer.getBean(ProductDAO.class);
        Product p = pdao.finById(1);
        List<Product> lp = pdao.findAll();
        for (Product x : lp) System.out.println(x.getName());
        System.out.println("This product has " + p.getReviews().size() + "Reviews");
       // pdao.deleteById(1);
    */ 
     
  
  }
}
