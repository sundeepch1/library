package com.skc.library.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skc.library.domain.Response;
import com.skc.library.exception.UnauthorizedException;
import com.skc.library.model.User;
import com.skc.library.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value="/api/users")
	@PreAuthorize("hasAnyRole('ADMIN','LIBRARIAN')")
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

	@GetMapping(value="/api/user/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable("userId") Long userId){
		try {
			
			User user = userService.getUserById(userId);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (Exception e) {
			throw new UnauthorizedException(e.getMessage());
		}
	}
	
	@PutMapping(value="/api/user")
	public ResponseEntity<Response> getUserById(@RequestBody User user){
		try {
			
			Boolean status = userService.updateUser(user);
			return new ResponseEntity<Response>(new Response(status.toString()) ,HttpStatus.OK);
		} catch (Exception e) {
			throw new UnauthorizedException(e.getMessage());
		}
	}
	
	@PutMapping(value="/api/changepassword")
	public ResponseEntity<Response> getUserById(@RequestBody HashMap<String, String> user){
		try {
			Boolean status = userService.changePassword(user);
			return new ResponseEntity<Response>(new Response(status.toString()) ,HttpStatus.OK);
		} catch (Exception e) {
			throw new UnauthorizedException(e.getMessage());
		}
	}
	
	@DeleteMapping(value="/api/user/{userId}")
	public ResponseEntity<Response> deleteUserById(@PathVariable("userId") Long userId){
		try {
			userService.deleteUserById(userId);
			return new ResponseEntity<Response>(new Response("user deleted successfully"), HttpStatus.OK);
		} catch (Exception e) {
			throw new UnauthorizedException(e.getMessage());
		}
	}
}
