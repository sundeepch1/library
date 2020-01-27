package com.skc.library.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skc.library.domain.UserDTO;
import com.skc.library.exception.UnauthorizedException;
import com.skc.library.model.User;
import com.skc.library.security.JwtTokenUtil;
import com.skc.library.security.JwtUser;

@RestController
public class AuthenticationController {

	@Value("${jwt.header}")
	private String tokenHeader;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@PostMapping(value="/api/login")
	public ResponseEntity<UserDTO> login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response){
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
			final JwtUser userDetails = (JwtUser) authentication.getPrincipal();
			SecurityContextHolder.getContext().setAuthentication(authentication);
			final String token = jwtTokenUtil.generateToken(userDetails);
			response.setHeader("Token", token);
			return new ResponseEntity<UserDTO>(new UserDTO(userDetails.getUser(), token), HttpStatus.OK);
		} catch (Exception e) {
			throw new UnauthorizedException(e.getMessage());
		}
	}
}
