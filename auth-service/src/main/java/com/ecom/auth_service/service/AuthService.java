package com.ecom.auth_service.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecom.auth_service.dto.LoginDto;
import com.ecom.auth_service.entity.User;
import com.ecom.auth_service.exception.IncorrectPassword;
import com.ecom.auth_service.exception.UserAlreadyExistsException;
import com.ecom.auth_service.exception.UserDoesntExistException;
import com.ecom.auth_service.repository.UserRepository;
import com.ecom.auth_service.security.Jwtutil;


@Service
public class AuthService {

	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final Jwtutil jwtutil;
	
	public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, Jwtutil jwtutil) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtutil = jwtutil;
	}
	
	public User registerUser(LoginDto loginDto) {
		if(userRepository.existsByUsername(loginDto.getUsername())) {
			throw new UserAlreadyExistsException("username " + loginDto.getUsername() + " already exists");
		}else {
			User user = new User();
			user.setEmail(loginDto.getEmail());
			user.setUsername(loginDto.getUsername());
			user.setPassword(passwordEncoder.encode(loginDto.getPassword()));
			user.setRole(loginDto.getRole());
			return userRepository.save(user);
		}
	}
	
	public String userLogin(LoginDto loginDto) {
		String username = loginDto.getUsername();
		String password = loginDto.getPassword();
		Optional<User> user = userRepository.findByUsername(username);
		if(user.isPresent()) {
			if(passwordEncoder.matches(password, user.get().getPassword())) {
				return jwtutil.generateToken(user.get().getUsername());
			}else {
				throw new IncorrectPassword("wrong password. try again!");
			}
		}else {
			throw new UserDoesntExistException(username);
		}
	}
	
	public User getUserById(long id){
		if(userRepository.existsById(id)) {
			return userRepository.findById(id).get();
		}else {
			throw new UserDoesntExistException("no user found with this id");
		}
	}
}

