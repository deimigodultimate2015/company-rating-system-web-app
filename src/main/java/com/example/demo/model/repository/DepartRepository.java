package com.example.demo.model.repository;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entities.Departs;

@Repository
public interface DepartRepository extends JpaRepository<Departs, Long> {
	
	Page<Departs> findByNameContaining(String name, Pageable pageable);
	Page<Departs> findAll(Pageable pageable);
	Optional<Departs> findByName(String name);
	
	@Query(value = "select count(dp.id) from departs dp", nativeQuery = true)
	int findDepartsCount();
	
}
