package com.example.ecommerce.web;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.ecommerce.domain.Customer;
import com.example.ecommerce.service.CustomerService;

/**
 * Invoke the Controller methods via HTTP.
 * Do not invoke the tourRatingService methods, use Mock instead
 * Created by Mary Ellen Bowman.
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CustomerControllerTest {
    private static final String CUSOMERS_URL = "/customers";

    //These Tour and rating id's do not already exist in the db
    private static final long CUSTOMER_ID = 999;
    private static final String NAME = "Test";
    private static final String EMAIL = "test@test.com";
    private static final String PHONE = "9999999999";

    @MockBean
    private CustomerService customerServiceMock;

    private Customer customerMock = new Customer(NAME, EMAIL, PHONE);

    @Autowired
    private TestRestTemplate restTemplate;


    @Before
    public void setupReturnValuesOfMockMethods() {
        when(customerServiceMock.findAll()).thenReturn(Arrays.asList(customerMock));
    }


    /**
     *  HTTP GET /customers
     */
    @Test
    public void getCustomers() {
        when(customerServiceMock.findAll()).thenReturn(Arrays.asList(customerMock, customerMock));

        ResponseEntity<List<Customer>> response = restTemplate.exchange(CUSOMERS_URL, HttpMethod.GET,null,
                                                    new ParameterizedTypeReference<List<Customer>>() {});

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().size(), is(2));
    }

    /**
     *  HTTP GET /customers/{id}
     */
    @Test
    public void getOne()  {

        when(customerServiceMock.findById(CUSTOMER_ID)).thenReturn(customerMock);

        ResponseEntity<Customer> response =
                restTemplate.getForEntity(CUSOMERS_URL + "/" + CUSTOMER_ID, Customer.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().getEmail(), is(EMAIL));
        assertThat(response.getBody().getPhone(), is(PHONE));
        assertThat(response.getBody().getName(), is(NAME));
    }
}