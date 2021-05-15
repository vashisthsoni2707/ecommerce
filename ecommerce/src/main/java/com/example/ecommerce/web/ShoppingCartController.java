package com.example.ecommerce.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.domain.Item;
import com.example.ecommerce.service.ShoppingCartService;
import com.sun.istack.NotNull;

@RestController
@RequestMapping(path = "/customers/{customerId}/cart")
public class ShoppingCartController {
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingCartController.class);
	
	protected ShoppingCartController() {

    }
	
	@GetMapping
    public @NotNull Iterable<Item> getCart(@PathVariable(value = "customerId") Long customerId) {
        return shoppingCartService.getCart(customerId);
    }
	
	@GetMapping("/calculate")
    public @NotNull double calculate(@PathVariable(value = "customerId") Long customerId) {
        return shoppingCartService.calculate(customerId);
    }
	
	/**
     * Create a Tour Rating.
     *
     * @param tourId
     * @param itemDto
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@PathVariable(value = "customerId") Long customerId, @RequestBody @Validated ItemDto itemDto) {
        LOGGER.info("POST /customers/{}/cart", customerId);
        Long id = (long) Item.idGenerator.getAndIncrement();
        shoppingCartService.addItem(customerId, new Item(id, itemDto.getProductId(), itemDto.getQuantity()));
    }
    
    /**
     * Create a Tour Rating.
     *
     * @param tourId
     * @param itemDto
     */
    @DeleteMapping("/{itemId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeProduct(@PathVariable(value = "customerId") Long customerId, @PathVariable(value = "itemId") Long itemId) {
        LOGGER.info("DELETE /customers/{}/cart/{}", customerId, itemId);
        shoppingCartService.removeItem(customerId, itemId);
    }
	
}
