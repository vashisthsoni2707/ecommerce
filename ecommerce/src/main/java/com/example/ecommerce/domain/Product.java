package com.example.ecommerce.domain;

import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Basic(optional = false)
    private String name;

    @NotNull
    @Basic(optional = false)
    private Double price;
	
	protected Product() {};
	
	
	public Product(String name, Double price) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.price = price;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		 return "Product{" +
         "id=" + id +
         ", name='" + name + '\'' +
         ", price='" + price + '\'' +
         '}';
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || this.getClass() != obj.getClass()) return false;
		Product c = (Product) obj;
		return Objects.equals(id, id) &&
			Objects.equals(name, c.name) &&
			Objects.equals(price, c.price);
				
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(id, name, price);
	}

}
