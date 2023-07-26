package com.mysmartshop.product.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mysmartshop.product.model.Product;
import com.mysmartshop.product.service.ProductService;
@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	ProductService service;
	
	@GetMapping
	public String greet() {
		return "Message from Product Service";
	}
	
	@GetMapping("/getall")
	public List<Product> getAvailableProducts(){
		return service.getAvailableProducts();
	}
	
	@GetMapping("/getbyid/{productId}")
	public Product getProductDetails(@PathVariable String productId) {
		return service.getProductDetails(productId);
	}
	@PostMapping
	public void addProduct(@RequestBody Product product) {
		service.addProduct(product);
	}
	
	@DeleteMapping("/{productId}")
	public void removeProduct(@PathVariable String productId) {
		service.removeProduct(productId);
	}
}
