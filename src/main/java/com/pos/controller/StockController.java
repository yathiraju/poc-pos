package com.pos.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.pos.dto.PersistedObjId;
import com.pos.dto.StockDTO;
import com.pos.entity.Stock;
import com.pos.service.ProductService;
import com.pos.service.StockService;

@Controller
public class StockController {

    @Autowired
    private StockService stockService;

    @RequestMapping(path = "/addStock", method = RequestMethod.GET)
    public ModelAndView showAddStockForm(@RequestParam(required = false) Long productId) throws Exception{

        ModelAndView modelAndView = new ModelAndView();
        StockDTO stockDTO = new StockDTO();
        if (productId != null) {
            stockDTO.setProductId(productId);
            
        }
        modelAndView.addObject("stockDTO", stockDTO);
        modelAndView.setViewName("addStock");
        return modelAndView;
    }

    @RequestMapping(path = "/addStock", method = RequestMethod.POST)
    public ModelAndView createUpdateStock(@Valid StockDTO stockDTO) {
        ModelAndView modelAndView = new ModelAndView();
        stockService.createStock(stockDTO);
        modelAndView.addObject("successMessage", "Stock has been updated successfully");
        modelAndView.addObject("stockDTO", new StockDTO());
        modelAndView.setViewName("addStock");
        return modelAndView;

    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Stock> getStocks() {
        List<Stock> list = stockService.getAllStocks();
        return list;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    public PersistedObjId createStock(@RequestBody @Valid StockDTO stockDTO) {

        Stock stock = stockService.createStock(stockDTO);
        return new PersistedObjId(stock.getId());
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Stock getStockById(@PathVariable("id") Integer id) {
        Stock stock = stockService.getStock(id).get();
        return stock;
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public void updateStock(@PathVariable("id") Long id, @RequestBody @Valid Stock stock) {
        stock.setId(id);
        stockService.updateStock(stock);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteStock(@PathVariable("id") Long id) {
        stockService.deleteStock(id);
    }
}
