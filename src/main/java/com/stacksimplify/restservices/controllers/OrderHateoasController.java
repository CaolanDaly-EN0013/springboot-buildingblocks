package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.Exceptions.UserNotFoundException;
import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.repositories.OrderRepository;
import com.stacksimplify.restservices.repositories.UserRepository;

@RestController
@RequestMapping(value = "/hateoas/users")
public class OrderHateoasController {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/{userId}/orders")
	public ResponseEntity<List<Order>> getAllOrders(@PathVariable Long userId) throws UserNotFoundException {
		Optional<User> optionalUser = userRepository.findById(userId);
		
		if(!optionalUser.isPresent()) {
			throw new UserNotFoundException("User not found.");
		}
		
		List<Order> allOrders = optionalUser.get().getOrders();
		return new ResponseEntity<List<Order>>(allOrders, HttpStatus.OK);
	}
}
