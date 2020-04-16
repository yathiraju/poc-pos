package com.pos.service;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pos.dto.ProductWithStockQuantity;
import com.pos.entity.Product;

/**
 * @author yathi
 *
 */
@Service
public interface ProductService {
	
    List<Product> getAllProducts();

    Optional<Product> getProduct(long productId);

    Product createProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(long productId);

    void deleteBulkProduct(List<Long> ids);

    List<Product> searchProduct(String displayName);

    Page<Product> findAllByPage(Pageable pageable);
    
    Page<ProductWithStockQuantity> findProductWithStockQuantityByPage(Pageable pageable);

   
}