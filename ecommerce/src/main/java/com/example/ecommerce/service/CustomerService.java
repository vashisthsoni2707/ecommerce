package com.example.ecommerce.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.domain.Customer;
import com.example.ecommerce.repo.CustomerRepository;

@Service
public class CustomerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

	@Autowired
	private CustomerRepository customerRepository;

	/**
	 * Create a new Customer in the database
	 *
	 * @param name
	 * @param email
	 * @param phone
	 */
	public void createNew(String name, String email, String phone) {
		LOGGER.info("Create customer {}", name);
		customerRepository.save(new Customer(name, email, phone));
	}

	/**
	 * 
	 * @return all the customers
	 */
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	
	/**
	 * 
	 * @param id
	 * @return Customer
	 */
	public Customer findById(Long id) {
		Customer c = verifyCustomer(id);
		return c;
	}
	
	/**
	 * 
	 * @param id
	 */
	public void deleteCustomer(Long id) {
		customerRepository.deleteById(id);
	}
	
	 /**
     * Verify and return the Customer given a customerId.
     *
     * @param productId
     * @return the found Product
     * @throws NoSuchElementException if no Tour found.
     */
    private Customer verifyCustomer(long customerId) throws NoSuchElementException {
        return customerRepository.findById(customerId).orElseThrow(() ->
                new NoSuchElementException("Customer does not exist " + customerId)
        );
    }
}
