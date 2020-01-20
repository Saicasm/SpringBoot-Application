package com.rakuten.spring.ui;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rakuten.spring.domain.Product;
import com.rakuten.spring.service.ProductService;

@Component("uiObj") // This is use to tell tht this is a bean class
public class ProductConsoleUi {
  ProductService service; // = new ProductServiceImpl();

  @Autowired
  public void setService(ProductService service) {
    this.service = service;
  }
  Scanner s = new Scanner(System.in);
  
  public void createproductWithUI() {
    System.out.println("Enter Name :");
    String name = s.nextLine();
    System.out.println("Enter Price:");
    float price = Float.parseFloat(s.nextLine());
    System.out.println("Enter Quantity :");
    int qty = Integer.parseInt(s.nextLine());
    Product p = new Product(name, price, qty);
    int id = service.addNewProduct(p);
    System.out.println("Created product with id " + id);
 
    
  }
  public void findProductById()
  {
	  
	  System.out.println("Enter the id to retrive id");
	    int x=Integer.parseInt(s.nextLine());
	    Product y=service.findById(x);
	    System.out.println("Id is " +y.getId()+" Name is " + y.getName()+ " Price is " + y.getPrice());
	   
  }
  public void findAll()
  {
	  Scanner s = new Scanner(System.in);
	  System.out.println("Enter the id to retrive id");
	    int x=Integer.parseInt(s.nextLine());
	    Product y=service.findById(x);
	    System.out.println("Id is " +y.getId()+" Name is " + y.getName()+ " Price is " + y.getPrice());
	  
  }
  public void deleteById()
  {
	  System.out.println("Enter the ID to be deleted ");
	  int z=Integer.parseInt(s.nextLine());
	  service.removeProduct(z);
	  s.close();
	  
  }

  public void getAll() {
    System.out.println(service.finadAll().toString());
  }
}
