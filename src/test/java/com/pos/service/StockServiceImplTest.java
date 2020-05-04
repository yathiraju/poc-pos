package com.pos.service;

import static org.mockito.Mockito.times;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.pos.dto.StockDTO;
import com.pos.entity.Product;
import com.pos.entity.Stock;
import com.pos.repository.ProductRepository;
import com.pos.repository.StockRepository;

/**
 * @author yathi
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class StockServiceImplTest {

	@Mock
	ProductRepository productRepository;

	@Mock
	StockRepository stockRepository;

	@InjectMocks
	StockServiceImpl stockServiceImpl;

	@Test
	public void testCreateStockService() {
		StockDTO stockDTO = new StockDTO();
		stockDTO.setProductId(10L);
		stockDTO.setQuantiy(10L);
		Product product = new Product();
		Optional<Product> pr = Optional.of(product);
		Mockito.when(productRepository.findById(Matchers.anyLong())).thenReturn(pr);
		Mockito.when(stockRepository.save(Matchers.anyObject())).thenReturn(Matchers.anyObject());
		stockServiceImpl.createStock(stockDTO);
		Mockito.verify(stockRepository, times(1)).save(Matchers.anyObject());
	}

	@Test
	public void testUpdateStock() {
		Stock stock = new Stock();
		stock.setId(10L);
		Optional<Stock> st = Optional.of(stock);
		Mockito.when(stockRepository.findById(Matchers.anyLong())).thenReturn(st);
		Mockito.when(stockRepository.save(Matchers.anyObject())).thenReturn(Matchers.anyObject());
		stockServiceImpl.updateStock(stock);
		Mockito.verify(stockRepository, times(1)).save(Matchers.anyObject());

	}

}
