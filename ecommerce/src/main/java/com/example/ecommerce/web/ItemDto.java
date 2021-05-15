package com.example.ecommerce.web;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.format.annotation.NumberFormat;


public class ItemDto {	
	@Max(10)
	@Min(1)
	private Integer quantity;
	
	@NumberFormat
	private Long productId;
	
	public ItemDto(Integer quantity, Long productId) {
		// TODO Auto-generated constructor stub
		this.quantity = quantity;
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
}
