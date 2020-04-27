package com.pos.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pos.dto.StockDTO;
import com.pos.entity.Stock;
import com.pos.service.StockService;

/**
 * @author yathi
 *
 */
@RestController
@RequestMapping("/stockapi")
public class StockRestController {
	
	@Autowired
	StockService stockService;
	
	@PostMapping(path="/createStock")
	public StockDTO createStock(@RequestBody StockDTO stockDTO) {
        stockService.createStock(stockDTO);
		return stockDTO;
		
	}
	
	@PutMapping(path="/updateStock")
	public Stock updateStock(@RequestBody Stock stock) {
        stockService.updateStock(stock);
		return stock;
		
	}

}
