package com.example.ecommerce.domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Item implements Serializable {
	private static final long serialVersionUID = 1L;
	public static volatile AtomicInteger idGenerator = new AtomicInteger(1);
	
	private Long id;
	
	private Integer quantity;
	
	private Long productId;
	
	public Item() {
		// TODO Auto-generated constructor stub
	}
	
	public Item (Long id, Long productId, Integer quantity) {
		this.id = id;
		this.productId = productId;
		this.quantity = quantity;
	}
	
	@Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", quantity='" + quantity + '\'' +
                ", productId='" + productId + '\'' +
                '}';
    }
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id);  
    }
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(id, productId);
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
