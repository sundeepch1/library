package com.skc.library.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skc.library.exception.UnauthorizedException;
import com.skc.library.model.User;
import com.skc.library.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value="/users")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<User>>  getAllUsers(){
		List<User> users = userService.findAll();
		
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@GetMapping(value="/api/user")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<User>  getUser(Principal principal){
		User user = userService.getUserByEmail(principal.getName());
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@GetMapping(value="/sundeep")
	public ResponseEntity<String> login(){
		try {
			return new ResponseEntity<String>(("i am getting"), HttpStatus.OK);
		} catch (Exception e) {
			throw new UnauthorizedException(e.getMessage());
		}
	}
}
