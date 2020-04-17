package com.pos.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pos.entity.Product;
import com.pos.service.ProductService;

/**
 * @author yathi
 *
 */
@RestController
@RequestMapping("/api")
public class ProductRestController {

	@Autowired
	private ProductService productService;

	@GetMapping(path = "/getProduct")
	public Product getProduct(@RequestParam(name = "productId") Long productId) {
		 if(productService.getProduct(productId).isPresent()) {
			 return productService.getProduct(productId).get();
		 }else {
			 return new Product();
		 }
		 
	}

	@PostMapping(path = "/createProduct")
	public Product createProduct(@RequestBody Product product) {
		return productService.createProduct(product);
	}

	@PutMapping(path = "/updateProduct")
	public Product updateProduct(@RequestBody Product product) {
		productService.updateProduct(product);
		return product;
	}
	
	@DeleteMapping(path = "/deleteProduct")
	public String deleteProduct(@RequestParam(name = "productId") Long productId) {
		 if(productService.getProduct(productId).isPresent()) {
			  productService.deleteProduct(productId);
			  return "Product deleted which is associated with productId:"+productId.toString();
		 }else {
			 return "There is no Product which is associated with productId:"+productId.toString();
		 }
		 
	}

}
