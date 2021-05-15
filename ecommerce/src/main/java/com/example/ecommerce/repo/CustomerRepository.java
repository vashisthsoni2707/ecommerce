package com.example.ecommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
}
