package com.example.demo.controller.api;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.message.request.LoginForm;
import com.example.demo.controller.message.response.JwtResponse;
import com.example.demo.model.dao.StaffMKXDao;
import com.example.demo.model.entities.Cloudinary;
import com.example.demo.model.entities.custom.StaffMKX;
import com.example.demo.model.repository.CloudinaryRepository;
import com.example.demo.model.repository.UserRepository;
import com.example.demo.security.jwt.JwtProvider;

@CrossOrigin("*")
@RestController
@RequestMapping("/homepage")
public class HomepageApi {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	StaffMKXDao staffMKXDao;
	
	@Autowired
	CloudinaryRepository cloudinaryRepository;
	
	@Autowired
	JwtProvider jwtProvider;
	
	@GetMapping("/top10")
	public List<StaffMKX> getTop10Staff() {
		List<StaffMKX> list = staffMKXDao.getTop10Staff();
		list.forEach(staff -> {
			Optional<Cloudinary> cloudinary = cloudinaryRepository.findById(staff.getStaff().getId());
			if(cloudinary.isPresent()) {
				staff.setCloudinary(cloudinary.get());
			} else {
				staff.setCloudinary(new Cloudinary(null, null, null));
			}
			
		});
		
		return list;
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = jwtProvider.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername()));
	}
	

	
}
