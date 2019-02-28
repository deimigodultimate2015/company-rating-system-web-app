package com.example.demo.controller.cloudinary;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Component
public class CloudinaryUtils {
	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
			"cloud_name", "dyglkqkac",
			  "api_key", "871822116264122",
			  "api_secret", "-fDAvKUdc53qGexp9soiwU1fIr8"));
	
	public com.example.demo.model.entities.Cloudinary uploadImage(MultipartFile file, String name) {
		try {
			@SuppressWarnings("rawtypes")
			Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("public_id", name));
			com.example.demo.model.entities.Cloudinary cloudinaryPro = new com.example.demo.model.entities.Cloudinary();
			cloudinaryPro.setStaffId(Long.parseLong(name));
			cloudinaryPro.setType(uploadResult.get("format").toString());
			cloudinaryPro.setVersion(uploadResult.get("version").toString());
			logger.info("done");
			return cloudinaryPro;
			
		} catch (IOException e) {
			logger.error("Cloudinary: " +e.getMessage());
		}
		return null;
	}
}
