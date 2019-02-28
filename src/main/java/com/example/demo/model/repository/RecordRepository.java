package com.example.demo.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entities.Records;

@Repository
public interface RecordRepository extends JpaRepository<Records, Long >{
	@Query(value = "select * from record  where staff_id like '5'", nativeQuery = true)
	Page<Records> findByStaffId(@Param("searchInput") String searchInput, Pageable pageable);
}
