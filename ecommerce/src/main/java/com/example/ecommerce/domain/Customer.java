package com.example.ecommerce.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;

	@Column
	private String email;
	
	@Column
	private String phone;
	
	protected Customer() {};
	
	public Customer(String name, String email, String phone) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.email = email;
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		 return "Customer{" +
         "id=" + id +
         ", name='" + name + '\'' +
         ", email='" + email + '\'' +
         ", phone='" + phone + '\'' +
         '}';
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || this.getClass() != obj.getClass()) return false;
		Customer c = (Customer) obj;
		return Objects.equals(id, id) &&
			Objects.equals(name, c.name) &&
			Objects.equals(email, c.email) &&
			Objects.equals(phone, c.phone);
				
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(id, name, email, phone);
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
