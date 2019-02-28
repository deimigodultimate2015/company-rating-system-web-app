package com.example.demo.controller.api;

import java.util.Optional;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.controller.cloudinary.CloudinaryUtils;
import com.example.demo.model.entities.Cloudinary;
import com.example.demo.model.repository.CloudinaryRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/image")
public class CloudinaryApi {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CloudinaryRepository cloudinaryRepository;
	
	@Autowired
	CloudinaryUtils cloudinaryUtils;
	
	@PostMapping(value = "/update/{staffId}")
	public ResponseEntity<String> updateImage(@PathVariable("staffId") Long id, @RequestParam("image") MultipartFile image) {
		
		try {
			
			if(image == null || ImageIO.read(image.getInputStream()) == null) {
				return new ResponseEntity<String>("This is an unacceptable file", HttpStatus.BAD_REQUEST);
			} else {
				Cloudinary cloudinary = cloudinaryUtils.uploadImage(image, id+"");
				if(cloudinary == null) {
					return new ResponseEntity<String>("Error occur when upload file", HttpStatus.BAD_REQUEST);
				}
				
				cloudinaryRepository.save(cloudinary);
				return new ResponseEntity<String>("File was saved successful!", HttpStatus.CREATED);
			}
			
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		
		return new ResponseEntity<String>("Error occur when upload file", HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/get/{staffId}")
	public ResponseEntity<Cloudinary> getImage(@PathVariable("staffId") Long id) {
		Optional<Cloudinary> cloud = cloudinaryRepository.findById(id);
		return new ResponseEntity<Cloudinary>(cloud.get(), HttpStatus.ACCEPTED);
	}
	
}
