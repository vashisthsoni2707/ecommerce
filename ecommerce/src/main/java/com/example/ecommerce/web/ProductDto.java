package com.example.ecommerce.web;


import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;

/**
 * Data Transfer Object for Rating a Tour.
 *
 * Created by Mary Ellen Bowman
 */

public class ProductDto {
	
	@Size(min=5, max = 255)
	private String name;

	@NumberFormat
	private Long quantity;
		
	/**
     * Constructor to fully initialize the ProductDto
     *
     * @param name name
     * @param quantity quantity
     */
    public ProductDto(String name, Long quantity) {
        this.name = name;
        this.quantity = quantity;
    }
    
    protected ProductDto() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
}
