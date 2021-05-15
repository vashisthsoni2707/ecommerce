package com.example.ecommerce.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.ecommerce.domain.Product;
import com.example.ecommerce.repo.ProductRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
	
	private static final long PRODUCT_ID = 1;
    private static final double PRICE = 10;
    private static final String NAME = "Test";
    private static final long NOT_A_PRODUCT_ID = 123;

    @Mock
    private ProductRepository productRepositoryMock;
    
    @InjectMocks
    private ProductService service;
    
    @Mock
    private Product productMock = new Product(NAME, PRICE);
    
    /**
     * Mock responses to commonly invoked methods.
     */
    @Before
    public void setupReturnValuesOfMockMethods() {
        when(productRepositoryMock.findById(PRODUCT_ID)).thenReturn(Optional.of(productMock));
        when(productMock.getId()).thenReturn(PRODUCT_ID);
        when(productRepositoryMock.findAll()).thenReturn(Arrays.asList(productMock));
    }

    //Happy Path delete existing TourRating.
    @Test
    public void delete() {
        List<Product> products = (List<Product>) service.getAllProducts();
        service.delete(products.get(0).getId());
        List<Product> updatedProducts = (List<Product>) service.getAllProducts();
        assertThat(updatedProducts.size(), is(products.size() - 1));
    }

    //UnHappy Path, Tour NOT_A_TOUR_ID does not exist
    @Test(expected = NoSuchElementException.class)
    public void deleteException() {
        service.delete(NOT_A_PRODUCT_ID);
    }


    //Happy Path to Create a new Tour Rating
    @Test
    public void createNew() {
        //would throw NoSuchElementException if TourRating for TOUR_ID by CUSTOMER_ID already exists
        service.save(productMock);

        //Verify New Tour Rating created.
        Product product = service.verifyProduct(PRODUCT_ID);
        assertThat(product.getId(), is(PRODUCT_ID));
        assertThat(product.getName(), is(NAME));
        assertThat(product.getPrice(), is(PRICE));
    }
}
