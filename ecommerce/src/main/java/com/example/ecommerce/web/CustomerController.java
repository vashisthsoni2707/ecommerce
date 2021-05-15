package com.example.ecommerce.web;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.domain.Customer;
import com.example.ecommerce.service.CustomerService;


@RestController
@RequestMapping(path = "/customers")
public class CustomerController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping
    public List<Customer> getAllCustomers() {
		List<Customer> list = new ArrayList<>(customerService.findAll());
        return list;
    }
	
	@GetMapping("/{id}")
    public Customer getCustomer(@PathVariable("id") Long id) {
        LOGGER.info("GET /customers/{id}", id);
        return this.customerService.findById(id);
    }
	
	/**
     * Create a Customer.
     *
     * @param tourId
     * @param ratingDto
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomer(@RequestBody @Validated CustomerDto customerDto) {
        LOGGER.info("POST /customers {}", customerDto);
        customerService.createNew(customerDto.getName(), customerDto.getEmail(), customerDto.getPhone());
    }
    
    /**
     * Exception handler if NoSuchElementException is thrown in this Controller
     *
     * @param ex
     * @return Error message String.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException ex) {
        LOGGER.error("Customer not found", ex);
        return ex.getMessage();

    }
}
