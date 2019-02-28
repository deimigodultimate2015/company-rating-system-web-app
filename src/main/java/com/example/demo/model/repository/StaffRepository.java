package com.example.demo.model.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entities.Staffs;

@Repository
public interface StaffRepository extends JpaRepository<Staffs, Long> {
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "update staffs set departs_id = null where departs_id = :departs_id", nativeQuery = true)
	void updateStaffWithDepartId(@Param("departs_id") Long departs_id);
	
	@Query("select st from Staffs st where st.name like :search or st.email like :search or st.phone like :search")
	Page<Staffs> findBy3Column(@Param("search") String search, Pageable pageable);

	Optional<Staffs> findById(Long id);
	
	boolean existsByPhone(String phoneNumber);
	boolean existsByEmail(String email);
}
