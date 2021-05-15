package com.example.ecommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
}
