package com.pos.service;

import static org.mockito.Mockito.times;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.pos.dto.InvoiceDTO;
import com.pos.entity.Invoice;
import com.pos.entity.Product;
import com.pos.entity.Stock;
import com.pos.repository.InvoiceRepository;
import com.pos.repository.ProductRepository;
import com.pos.repository.StockRepository;

/**
 * @author yathi
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class InvoiceServiceImplTest {

	@Mock
	ProductRepository productRepository;

	@Mock
	StockService stockService;

	@Mock
	StockRepository stockRepository;

	@Mock
	InvoiceRepository invoiceRepository;

	@InjectMocks
	InvoiceServiceImpl invoiceServiceImpl;

	@Test
	public void testcreateInvoice() {
		Product product = new Product();
		product.setId(20L);
		Stock stock = new Stock();
		stock.setId(30L);
		stock.setSalePrice(new BigDecimal(2500));
		InvoiceDTO invoiceDTO = new InvoiceDTO();
		invoiceDTO.setInvoiceQuantiy(10L);
		invoiceDTO.setProductId(5L);
		Optional<Product> pr = Optional.of(product);
		Optional<Stock> st = Optional.of(stock);

		Mockito.when(productRepository.findById(Matchers.anyLong())).thenReturn(pr);
		Mockito.when(stockRepository.findById(Matchers.anyLong())).thenReturn(st);
		Mockito.when(stockRepository.totalQuantityInStock(Matchers.anyLong())).thenReturn(15L);
		Mockito.when(stockService.createStock(Matchers.anyObject())).thenReturn(stock);
		Mockito.when(invoiceRepository.save(Matchers.anyObject())).thenReturn(new Invoice());
		invoiceServiceImpl.createInvoice(invoiceDTO);
		Mockito.verify(stockService, times(1)).createStock(Matchers.anyObject());

	}

}
