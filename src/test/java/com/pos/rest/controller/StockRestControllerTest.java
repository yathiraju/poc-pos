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
import com.pos.dto.StockDTO;
import com.pos.util.DateConversion;

public class StockRestControllerTest extends ApplicationConfigTest{
	
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	
	@Test
		public void testCreateStock() throws Exception {
			String uri="/stockapi/createStock";
			StockDTO stockDTO = new StockDTO();
			stockDTO.setStockEntryDate(new DateConversion().stringToDate("11/11/2020")); 
			stockDTO.setStockExpireDate(new DateConversion().stringToDate("11/11/2022")); 
			stockDTO.setProductId(11L);
			stockDTO.setQuantiy(10L);
			stockDTO.setPurchasePrice(new BigDecimal(70000));
			stockDTO.setSalePrice(new BigDecimal(90000));
		
			
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).content(asJsonString(stockDTO))
					                 .contentType(MediaType.APPLICATION_JSON)
					                 .accept(MediaType.APPLICATION_JSON))
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
