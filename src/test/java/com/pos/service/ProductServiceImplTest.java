package com.pos.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

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

}
