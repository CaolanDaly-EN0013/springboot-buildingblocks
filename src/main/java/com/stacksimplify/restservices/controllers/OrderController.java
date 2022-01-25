package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.Exceptions.OrderNotFoundException;
import com.stacksimplify.restservices.Exceptions.UserNotFoundException;
import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.repositories.OrderRepository;
import com.stacksimplify.restservices.repositories.UserRepository;

@RestController
@RequestMapping(value = "/users")
public class OrderController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@GetMapping("/{userId}/orders")
	public List<Order>getAllOrders(@PathVariable Long userId) throws UserNotFoundException {
		Optional<User> optionalUser = userRepository.findById(userId);
		
		if(!optionalUser.isPresent()) {
			throw new UserNotFoundException("User not found.");
		}
		
		return optionalUser.get().getOrders();
	}
	
	@PostMapping("{userId}/orders")
	public Order createOrder(@PathVariable Long userId, @RequestBody Order order) throws UserNotFoundException {
		Optional<User> optionalUser = userRepository.findById(userId);
		
		if(!optionalUser.isPresent()) {
			throw new UserNotFoundException("User not found.");
		}
		
		User user = optionalUser.get();
		order.setUser(user);
		return orderRepository.save(order);
	}
	
	@GetMapping("/{userId}/orders/{orderId}")
	public Optional<Order> getOrderById(@PathVariable Long userId, @PathVariable Long orderId) throws OrderNotFoundException {
		Optional<Order> order = orderRepository.findById(orderId);
		
		if(!order.isPresent()) {
			throw new OrderNotFoundException("Order not found in repository.");
		}
				
		return order;
	}
}
