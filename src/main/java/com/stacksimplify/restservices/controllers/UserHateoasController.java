package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;
import com.stacksimplify.restservices.services.UserService;

@RestController
@RequestMapping(value = "/hateoas/users")
@Validated
public class UserHateoasController {

	@Autowired
	private UserRepository userReposotry;

	@Autowired
	private UserService userService;

	// get user by id
	@GetMapping("/{id}")
	public EntityModel<User> getUserById(@PathVariable("id") @Min(1) Long id) {

		try {
			Optional<User> userOptional = userService.getUserById(id);
			User user = userOptional.get();
			Long userid = user.getId();
			Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
			user.add(selfLink);
			EntityModel<User> finalEntityModel = new EntityModel<User>(user);
			return finalEntityModel;

		} catch (UserNotFoundException ex) {

			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}

	}

	// getAllUser Method
	@GetMapping // ("/users")
	public EntityModel<User> getAllUsers() {

		List<User> allusers = userService.getAllUsers();
		for (User user : allusers) {
			// self Link
			Long userid = user.getId();
			Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
			user.add(selfLink);

			// Relationship Link with getAllOrders
			EntityModel<Order> orders = WebMvcLinkBuilder.methodOn(OrderHateoasController.class).getAllOrders(userid);
			Link ordersLinks = WebMvcLinkBuilder.linkTo(orders).withRel("all-order");
			user.add(ordersLinks);
		}
		// Self link for getAllUsers
		Link sekfLinkAllUsers = WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();
		EntityModel<User> finalEntityModel = new EntityModel<User>(allusers, sekfLinkAllUsers);
		return finalEntityModel;
	}
}
