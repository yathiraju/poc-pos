package com.pos.service;


import com.pos.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yathi
 *
 */
@Service
@Transactional
public interface ProductService {
	
	
    List<Product> getAllProducts();

    Product getProduct(long productId);

    Product createProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(long productId);

    void deleteBulkProduct(List<Long> ids);

    List<Product> searchProduct(String displayName);

    Page<Product> findAllByPage(Pageable pageable);

   
}