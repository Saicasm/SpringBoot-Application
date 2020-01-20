package com.rakuten.spring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Review {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;

  String author;
  String content;
  float starts;

  @ManyToOne
  @JoinColumn(name = "product_id")
  Product product;

  public Review() {
    // TODO Auto-generated constructor stub
  }

  public Review(String author, String content, float starts) {
    super();
    this.author = author;
    this.content = content;
    this.starts = starts;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public float getStarts() {
    return starts;
  }

  public void setStarts(float starts) {
    this.starts = starts;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }
}
