package com.example.ecommerce.service;

import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.domain.Product;
import com.example.ecommerce.repo.ProductRepository;

@Service
@Transactional
public class ProductService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    @Autowired
	private ProductRepository productRepository;

    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(long id) {
        Product product = verifyProduct(id);
    	return product; 
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }
    
    public void delete(long productId) throws NoSuchElementException {
        LOGGER.info("Delete Product for product {}",productId);
        Product product = verifyProduct(productId);
        productRepository.delete(product);
    }
    
    /**
     * Verify and return the Product given a productId.
     *
     * @param productId
     * @return the found Product
     * @throws NoSuchElementException if no Tour found.
     */
     Product verifyProduct(long productId) throws NoSuchElementException {
        return productRepository.findById(productId).orElseThrow(() ->
                new NoSuchElementException("Product does not exist " + productId)
        );
    }
}