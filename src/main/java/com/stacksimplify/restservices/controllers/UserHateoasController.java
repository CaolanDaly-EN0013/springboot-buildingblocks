package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.Exceptions.UserNotFoundException;
import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.repositories.UserRepository;
import com.stacksimplify.restservices.services.UserService;

@RestController
@RequestMapping(value = "/hateoas/users")
@Validated
public class UserHateoasController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<CollectionModel<User>> getAllUsers() throws UserNotFoundException{
		List<User> allUsers =  userService.getAllUsers();
		
		for(User user : allUsers) {
			Long userId = user.getUserId();
			Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userId).withSelfRel();
			user.add(selfLink);
			
			ResponseEntity<List<Order>> orders = WebMvcLinkBuilder.methodOn(OrderHateoasController.class)
					.getAllOrders(userId);
			
			Link ordersLink = WebMvcLinkBuilder.linkTo(orders).withRel("all-orders");
			user.add(ordersLink);
			
		}
		
		Link selfLinkGetAllUsers = WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();
		
		return ResponseEntity.ok(CollectionModel.of(allUsers, selfLinkGetAllUsers));
		//return new ResponseEntity<List<User>>(allUsers, selfLinkGetAllUsers, HttpStatus.OK);

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") @Min(1) Long id){
		try {
			Optional<User> userOptional = userService.getUserById(id);
			User user = userOptional.get();
			Long userId = user.getUserId();
			Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userId).withSelfRel();
			user.add(selfLink);
		
			return new ResponseEntity<User>(user, HttpStatus.OK);
				
			
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
}
