package com.pos.rest.controller;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pos.ApplicationConfigTest;
import com.pos.entity.Product;

public class ProductRestControllerTest extends ApplicationConfigTest{
	Long productId;
	
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	
	@Test
	public void testCreateProduct() throws Exception {
		String uri="/api/createProduct";
		Product product = new Product();
		product.setDisplayName("Iphone");
		product.setVendor("Apple");
		product.setCatagory("mobile");
		product.setBrand("Apple");
		product.setDescription("this is latest iphone 11 series");
		product.setWeight(new BigDecimal(12.5));
		product.setBarcode("2e5thgj");
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).content(asJsonString(product))
				                 .contentType(MediaType.APPLICATION_JSON)
				                 .accept(MediaType.APPLICATION_JSON))
				                 .andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		productId=product.getId();
	}
	
	@Test
	public void testUpdateProduct() throws Exception {
		String uri="/api/updateProduct";
		Product product = new Product();
		product.setId(10L);
		product.setDisplayName("Iphone");
		product.setVendor("Apple");
		product.setCatagory("mobile");
		product.setBrand("Apple");
		product.setDescription("this is latest iphone 11 series");
		product.setWeight(new BigDecimal(12.5));
		product.setBarcode("2e5thgj");
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).content(asJsonString(product))
				                 .contentType(MediaType.APPLICATION_JSON)
				                 .accept(MediaType.APPLICATION_JSON))
				                 .andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
	}

	
	@Test
	public void testGetProduct() throws Exception {
		String uri="/api/getProduct?productId=1";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
	}
	
	
	@Test
	public void testDeleteProduct() throws Exception {
		String uri="/api/deleteProduct?productId=10";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

}
