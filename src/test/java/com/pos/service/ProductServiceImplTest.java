package com.pos.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pos.entity.Product;
import com.pos.repository.ProductRepository;

/**
 * @author yathi
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

	@Mock
	ProductRepository productRepository;

	@InjectMocks
	ProductServiceImpl productServiceImpl;
	

	@Test
	public void testCreateProduct_productnotexists() {
		Product product = new Product();
		product.setBarcode("5e6t7y");
		Mockito.when(productRepository.isProductExists(Matchers.anyString())).thenReturn(false);
		Mockito.when(productRepository.save(product)).thenReturn(product);
		assertEquals("5e6t7y", productServiceImpl.createProduct(product).getBarcode());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateProduct_productexists() {
		Product product = new Product();
		product.setBarcode("5e6t7y");
		Mockito.when(productRepository.isProductExists(Matchers.anyString())).thenReturn(true);
		productServiceImpl.createProduct(product).getBarcode();
	}
	
	@Test
	public void testUpdateProduct() {
		
		Product product = Mockito.mock(Product.class);
		product.setBarcode("2errty");
		Optional<Product> pr = Optional.of(product);
		Mockito.when(productRepository.findById(Matchers.anyLong())).thenReturn(pr);
		Mockito.when(productRepository.save(product)).thenReturn(product);
		productServiceImpl.updateProduct(product);
		Mockito.verify(productRepository,times(1)).save(product);
	}
	
	@Test
	public void testgetProduct() {
		Product product = Mockito.mock(Product.class);
		product.setBarcode("2errty");
		Optional<Product> pr = Optional.of(product);
		Mockito.when(productRepository.findById(Matchers.anyLong())).thenReturn(pr);
		productServiceImpl.getProduct(10L);
		Mockito.verify(productRepository,times(1)).findById(10L);
	}

	@Test
	public void testgetAllProducts() {
		productServiceImpl.getAllProducts();
		Mockito.verify(productRepository,times(1)).findAll();
	}
	
	@Test
	public void testfindAllByPage() {
		Pageable pageable = null;
		productServiceImpl.findAllByPage(pageable);
		Mockito.verify(productRepository,times(1)).findAll(pageable);
	}
	

}
