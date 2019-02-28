package com.example.demo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entities.Cloudinary;

@Repository
public interface CloudinaryRepository extends JpaRepository<Cloudinary, Long> {

}
