

package com.rakuten.spring;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mockito.Mockito;

import com.rakuten.spring.dal.ProductDAO;
import com.rakuten.spring.domain.Product;
import com.rakuten.spring.service.ProductServiceImpl;

public class ProductServiceImplTest
{

  @Test
  public void addNewProduct_Returns_Valid_Id_When_ProductValue_GTEQ_MinValue() 
  {
	  //fail("Not yet implemented");
	  
	  //Arrange
	  ProductServiceImpl service = new ProductServiceImpl();
	  Product toBeAdded = new Product("namana", 10000, 1);   // notice if 10000 * 1 is >= 10000
	  
	  //use mockito to dynamically train mock object
	  ProductDAO mockDAO = Mockito.mock(ProductDAO.class);
	  Product saved = new Product("namana", 10000, 1);
	  saved.setId(1);
	  Mockito.when(mockDAO.save(toBeAdded)).thenReturn(saved);
	  service.setDao(mockDAO);
	  
	  //Act
	  int id = service.addNewProduct(toBeAdded);
	  
	  //Assert
	  assertTrue(id>0);
  }
  
  

  @Test(expected = IllegalArgumentException.class)
  public void addNewProduct_Throws_When_ProductValue__LT_MinValue()
  {
	  //Arrange
	  ProductServiceImpl service = new ProductServiceImpl();
	  Product toBeAdded = new Product("namana", 9999, 1);    //notice if 9999 * 1 is >= 10000
	  
	  //Act
	  service.addNewProduct(toBeAdded);
	  
	  //Assert
	  
	  
  }

@Test
public void removeProduct_Removes_When_ProductValue_LT_MinValue() {
	ProductServiceImpl service = new ProductServiceImpl();
	Product existing = new Product("Test",9999,1);
	existing.setId(1);
	ProductDAO mockDAO =Mockito.mock(ProductDAO.class);
	Mockito.when(mockDAO.finById(1)).thenReturn(existing);
	service.setDao(mockDAO);
	
	int id=1;
	
	service.removeProduct(1);
	
	Mockito.verify(mockDAO).deleteById(id);
	
	
}
@Test(expected = IllegalStateException.class)
public void removeProduct_Removes_When_ProductValue_GT_MinValue() {
	ProductServiceImpl service = new ProductServiceImpl();
	Product existing = new Product("Test",100000,1);
	existing.setId(1);
	ProductDAO mockDAO =Mockito.mock(ProductDAO.class);
	Mockito.when(mockDAO.finById(1)).thenReturn(existing);
	service.setDao(mockDAO);
	
	int id=1;
	
	service.removeProduct(1);
	
	Mockito.verify(mockDAO).deleteById(id);
	
	
}
@Test
public void removeProduct_Removes_When_Null() {
	
	ProductServiceImpl service = new ProductServiceImpl();
	Product existing = null;
    /*existing.setId(0);*/
    ProductDAO mockDAO = Mockito.mock(ProductDAO.class);
	Mockito.when(mockDAO.finById(1)).thenReturn(existing);
	service.setDao(mockDAO);
	
	int id=1;
	
	service.removeProduct(1);
	
	Mockito.verify(mockDAO,Mockito.times(0)).deleteById(id);

	
	
	
}
}

