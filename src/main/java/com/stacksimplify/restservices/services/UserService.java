package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserExistsException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;

//Service
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	// getAllUser Method
	public List<User> getAllUsers() {

		return userRepository.findAll();
	}

	// CreateUser Method
	public User createUser(User user) throws UserExistsException {

		// if User exists using username
		User existingUser = userRepository.findByUsername(user.getUsername());
		if (existingUser != null) {
			throw new UserExistsException("User already exists in repository");
		}
		return userRepository.save(user);
	}

	// Getuserbyid
	public Optional<User> getUserById(Long id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User not found in UserRepository");
		}
		return user;
	}

	// Updateuserbyid
	public User updateUserById(Long id, User user) throws UserNotFoundException {
		Optional<User> optionalUser = userRepository.findById(id);
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("User not found in UserRepository, provide correct userId ");
		}
		user.setId(id);
		return userRepository.save(user);
	}

	// DeleteUserById
	public void deleteUserById(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if (!optionalUser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"User not found in UserRepository, provide correct userId ");
		}
		userRepository.deleteById(id);

	}

	// get user by username
	public User getUserByUsername(String username) {

		return userRepository.findByUsername(username);
	}

	//

}
