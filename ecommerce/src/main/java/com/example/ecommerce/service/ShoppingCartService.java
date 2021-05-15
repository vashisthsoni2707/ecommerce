package com.example.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.domain.Item;

@Service
@Transactional
public class ShoppingCartService {
	public static ConcurrentMap<Long, List<Item>> cartMap = new ConcurrentHashMap<>();
	
	@Autowired
	private ProductService productService;

	public void addItem(Long customerId, Item item) {
		if (cartMap.get(customerId) != null)
			cartMap.get(customerId).add(item);
		else {
			List<Item> list = new ArrayList<Item>();
			list.add(item);
			cartMap.put(customerId, list);
		}	
	}

	public Iterable<Item> getCart(Long customerId) {
		return cartMap.getOrDefault(customerId, new ArrayList<Item>());
	}

	public void removeItem(Long customerId, Long itemId) {
		List<Item> list = cartMap.get(customerId).parallelStream()
				.filter(x -> x.getId() != itemId)
				.collect(Collectors.toList());
		cartMap.put(customerId, (ArrayList<Item>) list);
		
	}
	
	public Double calculate(Long customerId) {
		Double totalAmount = cartMap.get(customerId).parallelStream()
				.mapToDouble(x -> productService.getProduct(x.getProductId()).getPrice() * x.getQuantity())
				.sum();
		return totalAmount;
	}
}
