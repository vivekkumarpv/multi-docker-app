package com.mysmartshop.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mysmartshop.product.model.Product;


public interface ProductService {
	
	public List<Product> getAvailableProducts();

	public Product getProductDetails(String productId);

	public void addProduct(Product product);

	public void removeProduct(String productId);
}
