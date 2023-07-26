package com.mysmartshop.cart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mysmartshop.cart.dto.Order;
import com.mysmartshop.cart.dto.Product;
import com.mysmartshop.cart.model.CartItem;
import com.mysmartshop.cart.repository.CartItemRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	CartItemRepository itemRepo;

	@Autowired
	RestTemplate productServiceClient;

	@Override
	public List<CartItem> addToCart(String productId) {
		Optional<CartItem> checkedItem = itemRepo.findByProductId(productId);
		if (checkedItem.isEmpty()) {
			CartItem item = new CartItem();
			item.setProductId(productId);
			item.setQuantity(1);
			item.setTotalPrice(fetchPrice(productId));
			itemRepo.save(item);
		} else {
			CartItem item = checkedItem.get();
			updateQuantity(productId, item.getQuantity() + 1);

		}
		return getAllItems();
	}

	@Override
	public List<CartItem> removeFromCart(String productId) {

		Optional<CartItem> checkItem = itemRepo.findByProductId(productId);
		if (checkItem.isPresent()) {
			CartItem item = checkItem.get();
			itemRepo.delete(item);
		}

		return getAllItems();
	}

	@Override
	public List<CartItem> updateQuantity(String productId, int quantity) {
		Optional<CartItem> checkItem = itemRepo.findByProductId(productId);

		if (checkItem.isPresent()) {
			CartItem item = checkItem.get();
			float unitPrice = item.getTotalPrice() / item.getQuantity();
			item.setQuantity(quantity);
			item.setTotalPrice(unitPrice * quantity);
			itemRepo.save(item);
		}

		return getAllItems();

	}

	@Override
	public float calculateTotalCost() {
		return itemRepo.getTotalCartValue();

	}

	public List<CartItem> getAllItems() {
		return itemRepo.findAll();
	}

	@CircuitBreaker(fallbackMethod = "fetchPriceFallback", name = "cb-product")
	private float fetchPrice(String productId) {

		Product product = productServiceClient.getForObject("http://product-ms/api/product/getbyid" + productId,
				Product.class);
		if (product != null)
			return product.getPrice();
		return 0;
	}

	private float fetchPriceFallback(String productId, Throwable t) {
		Product product = new Product(productId, "Dummy Product", 0, "A dummy product");
		System.err.println(t.getMessage());
		System.out.println("Response from Fallback");
		System.out.println(product);
		return product.getPrice();
	}

	
	@Override
	public Order placeOrder(List<CartItem> cartItems) {
		return null;
	}

}
