package com.rakuten.spring.web;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.hamcrest.CoreMatchers;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakuten.spring.domain.Product;
import com.rakuten.spring.service.ProductService;
import com.rakuten.spring.web.ProductController;


@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerUnitTest {
  @Autowired MockMvc mockMvc;
  @MockBean ProductService service;

  @Test
  public void getProductId_Returns_OK_With_Correct_Product_If_Found() throws Exception {
    // Arrange
    Product found = new Product("Test", 123, 100);
    
    int id = 1;
    found.setId(id);
    Mockito.when(service.findById(id)).thenReturn(found);
    // ACT
    mockMvc
        .perform(get("/products/{id}", id))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(id)));
  }
  @Test
  public void  getProductId_Returns_Not_Found_If_Not_Found() throws Exception {
	  int id =1;
	  Mockito.when(service.findById(id)).thenReturn(null);
	  mockMvc
	  .perform(get("/products/{id}",id))
	  .andExpect(MockMvcResultMatchers.status().isNotFound());
  }
  @Test
  public void addProducts_Returns_Created_When_Successfull() throws Exception {
	  Product add=new Product("Test",123,100);
	  
	  Mockito.when(service.addNewProduct(Mockito.any(Product.class))).thenReturn(3);
	  String json=toJson(add);
	  mockMvc
	  .perform(MockMvcRequestBuilders.post("/products/").contentType("application/json").content(json))
	  .andExpect(MockMvcResultMatchers.status().isCreated())
	  .andExpect(MockMvcResultMatchers.header().string("location", "/products/3"));
	  
	  
  }
  
  @Test
  public void addProducts_Returns_BAD_Reqquest_When_Unsuccessfull() throws Exception {
	  Product add=new Product("Test",1,100);
	  
	  Mockito.when(service.addNewProduct(Mockito.any(Product.class))).thenThrow(new IllegalArgumentException());
	  String json=toJson(add);
	  mockMvc
	  .perform(MockMvcRequestBuilders.post("/products/").contentType("application/json").content(json))
	  .andExpect(MockMvcResultMatchers.status().isBadRequest());
	  //.andExpect(MockMvcResultMatchers.header().string("location", "/products/3"));
	  
	  
  }
  @Test
  public void removeProduct_Return_NO_Content_When_Successful() throws Exception {
	 int id=1;
	 Mockito.doNothing().when(service).removeProduct(id);
	 mockMvc.perform(MockMvcRequestBuilders.delete("/products/"+id)).andExpect(MockMvcResultMatchers.status().isNoContent()); 
	  
  }
  @Test
  public void removeProduct_Return_Conflict_When_Unsuccessful() throws Exception {
	 int id=1;
	 Mockito.doThrow(new IllegalStateException()).when(service).removeProduct(id);
	 mockMvc.perform(MockMvcRequestBuilders.delete("/products/"+id)).andExpect(MockMvcResultMatchers.status().isConflict()); 
	  Mockito.verify(service).removeProduct(id);
  }
  @Test
  public void removeProduct_Return_NOT_Found_When_Unsuccessful() throws Exception {
	 int id=1;
	 Mockito.doThrow(new NullPointerException()).when(service).removeProduct(id);
	 mockMvc.perform(MockMvcRequestBuilders.delete("/products/"+id)).andExpect(MockMvcResultMatchers.status().isNotFound()); 
	  Mockito.verify(service).removeProduct(id);
  }
  public static String toJson(Object o) throws JsonProcessingException {

	    ObjectMapper mapper = new ObjectMapper();

	    try {
	      // convert user object to json string and return it
	      return mapper.writeValueAsString(o);
	    } catch (JsonGenerationException | JsonMappingException e) {
	      // catch various errors
	      e.printStackTrace();
	    }
	    return null;
	  }
}
