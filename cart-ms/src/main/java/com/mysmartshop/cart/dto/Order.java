package com.mysmartshop.cart.dto;

import java.util.List;

import com.mysmartshop.cart.model.CartItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Order {

	private int _id;
	private String orderId;
	private String status;
	private List<CartItem> orderItems;

}
