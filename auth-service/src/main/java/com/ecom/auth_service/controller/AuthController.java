package com.ecom.auth_service.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.auth_service.dto.LoginDto;
import com.ecom.auth_service.entity.User;
import com.ecom.auth_service.service.AuthService;

@RestController
@RequestMapping("/auth/v1")
public class AuthController {

	@Autowired
	AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody LoginDto loginDto){
		User user = authService.registerUser(loginDto);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> userLogin(@RequestBody LoginDto loginDto){
		String token = authService.userLogin(loginDto);
		return new ResponseEntity<>(token, HttpStatus.OK);
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") long id){
		User user = authService.getUserById(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}