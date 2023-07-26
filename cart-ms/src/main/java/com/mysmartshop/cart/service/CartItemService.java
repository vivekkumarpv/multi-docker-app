package com.mysmartshop.cart.service;

import java.util.List;

import com.mysmartshop.cart.dto.Order;
import com.mysmartshop.cart.model.CartItem;


public interface CartItemService {
	public List<CartItem> addToCart(String productId);
	public List<CartItem> removeFromCart(String productId);
	public List<CartItem> updateQuantity(String productId,int quantity);
	public float calculateTotalCost();
	public List<CartItem> getAllItems();
	public Order placeOrder(List<CartItem> cartItems);
	
	
//	 +addToCart(productId: String): void
//	  +removeFromCart(productId: String): void
//	  +updateQuantity(productId: String, quantity: int): void
//	  +calculateTotalCost(): float
}
