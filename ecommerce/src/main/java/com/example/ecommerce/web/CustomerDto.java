package com.example.ecommerce.web;


import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object for Rating a Tour.
 *
 * Created by Mary Ellen Bowman
 */

public class CustomerDto {
	
	@Size(max = 255)
	private String name;

	@Email
	private String email;
	
	@Pattern(regexp="(^$|[0-9]{10})")
	private String phone;
	
	/**
     * Constructor to fully initialize the RatingDto
     *
     * @param name name
     * @param email email
     * @param phone phone
     */
    public CustomerDto(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    
    protected CustomerDto() {}

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
