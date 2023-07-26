package com.mysmartshop.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mysmartshop.product.model.Product;

public interface ProductRepo extends JpaRepository<Product, String>{

}
