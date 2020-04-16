package com.pos.service;

import com.pos.dto.StockDTO;
import com.pos.entity.Stock;

import java.util.List;
import java.util.Optional;

public interface StockService {

    List<Stock> getAllStocks();

    Optional<Stock> getStock(long stockId);

    Stock createStock(StockDTO stockDTO);

    void updateStock(Stock stock);

    void deleteStock(long stockId);

    Long totalQuantityInStock(Long productId);


}
