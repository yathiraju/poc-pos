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
public interface ProductService {
	
	@Transactional
    List<Product> getAllProducts();

	@Transactional
    Product getProduct(long productId);

	@Transactional
    Product createProduct(Product product);

	@Transactional
    void updateProduct(Product product);

	@Transactional
    void deleteProduct(long productId);

	@Transactional
    void deleteBulkProduct(List<Long> ids);

	@Transactional
    List<Product> searchProduct(String displayName);

	@Transactional
    Page<Product> findAllByPage(Pageable pageable);

   
}