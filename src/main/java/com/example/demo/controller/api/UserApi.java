package com.example.demo.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.message.request.SignupForm;
import com.example.demo.controller.message.response.ResponseMessage;
import com.example.demo.model.entities.Users;
import com.example.demo.model.repository.UserRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserApi {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("/get")
	public List<Users> getAllStaff() {
		return userRepository.findAll();
	}
	
	@PostMapping(value = "/add") 
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupForm signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
					HttpStatus.BAD_REQUEST);
		}
 
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
					HttpStatus.BAD_REQUEST);
		}
 
		Users users = new Users(null, signUpRequest.getEmail(), signUpRequest.getUsername(),
				passwordEncoder.encode(signUpRequest.getPassword()), signUpRequest.getFullname());
 
		
		userRepository.save(users);
 
		return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
	}
	
	
}
