package com.pos.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pos.dto.StockDTO;
import com.pos.entity.Product;
import com.pos.entity.Stock;
import com.pos.repository.ProductRepository;
import com.pos.repository.StockRepository;

/**
 * @author yathi
 *
 */
@Transactional
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ProductRepository productRepository;


    @Override
    public Optional<Stock> getStock(long stockId) {
        return stockRepository.findById(stockId);
    }

    @Override
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    @Override
    public Stock createStock(StockDTO stockDTO) {

        Stock stockById = new Stock();
        stockById.setPurchasePrice(stockDTO.getPurchasePrice());
        stockById.setSalePrice(stockDTO.getSalePrice());
        stockById.setStockEntryDate(stockDTO.getStockEntryDate());
        stockById.setStockExpireDate(stockDTO.getStockExpireDate());
        stockById.setQuantiy(stockDTO.getQuantiy());
        Optional<Product> product = productRepository.findById(stockDTO.getProductId());
        stockById.setProduct(product.get());
        stockRepository.save(stockById);
        return stockById;
    }

    @Override
    public void updateStock(Stock stock) {

        Optional<Stock> st = getStock(stock.getId());
        Stock stockById=st.get();
        stockById.setPurchasePrice(stock.getPurchasePrice());
        stockById.setSalePrice(stock.getSalePrice());
        stockById.setStockEntryDate(stock.getStockEntryDate());
        stockById.setStockExpireDate(stock.getStockExpireDate());
        stockById.setQuantiy(stock.getQuantiy());

        stockRepository.save(stockById);
    }

    @Override
    public void deleteStock(long stockId) {
        stockRepository.deleteById(stockId);
    }

    @Override
    public Long totalQuantityInStock(Long productId) {
        return stockRepository.totalQuantityInStock(productId);
    }
}
