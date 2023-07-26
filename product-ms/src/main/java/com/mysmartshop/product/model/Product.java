package com.mysmartshop.product.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
	
	@Id
	private String productId;
	private String Name;
	private float price;
	private String description;
	
}
