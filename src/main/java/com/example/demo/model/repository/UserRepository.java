package com.example.demo.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entities.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{
	
	Optional<Users> findByUsername(String username);
	
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);
	
}
