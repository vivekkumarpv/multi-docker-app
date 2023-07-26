package com.mysmartshop.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysmartshop.product.model.Product;
import com.mysmartshop.product.repository.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepo repo;

	@Override
	public List<Product> getAvailableProducts() {
		return repo.findAll();
	}

	@Override
	public Product getProductDetails(String productId) {
		return repo.findById(productId).get();
	}

	@Override
	public void addProduct(Product product) {
		repo.save(product);
		
	}

	@Override
	public void removeProduct(String productId) {
		repo.deleteById(productId);
		
	}

}
