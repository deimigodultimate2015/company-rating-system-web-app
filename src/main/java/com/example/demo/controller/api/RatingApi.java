package com.example.demo.controller.api;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.message.SearchData;
import com.example.demo.controller.responseMessage.RecordListingResponse;
import com.example.demo.model.dao.RecordsDao;
import com.example.demo.model.entities.Records;
import com.example.demo.model.entities.Staffs;
import com.example.demo.model.repository.RecordRepository;
import com.example.demo.model.repository.StaffRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/rating")
public class RatingApi {
	
	@Autowired
	RecordRepository recordRepository;
	
	@Autowired
	StaffRepository staffRepository;
	
	@Autowired
	RecordsDao recordDao;
	
	@Autowired
	public JavaMailSender emailSender;
	
	@PostMapping(value = "/create/{staffId}")
	public ResponseEntity<String> createRating(@PathVariable Long staffId, @RequestBody Records record) {
		Optional<Staffs> staffOptional = staffRepository.findById(staffId);
		if(!staffOptional.isPresent()) {
			return new ResponseEntity<String>("Create record failed, staff doesn't exist", HttpStatus.BAD_REQUEST);
		}
		
		Records _record = record;
		_record.setStaff(staffOptional.get());
	
		recordRepository.save(_record);
		sendSimpleMessage(staffOptional.get().getEmail(), _record.isType(), _record.getReason(), _record.getDate().toString());
		return new ResponseEntity<String>("Record was created successful!", HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/search")
	public RecordListingResponse searchRecords(@RequestBody SearchData searchData) {
		return recordDao.searchRecordsByStaffId(searchData, false);
	}
	
	@PostMapping(value = "/searchById")
	public RecordListingResponse searchRecordsByStaffId(@RequestBody SearchData searchData) {
		return recordDao.searchRecordsByStaffId(searchData, true);
	}
	
	
	private void sendSimpleMessage(String email, boolean type, String reason, String date) {
		SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(email); 
        String gb = type?"good":"bad";
        message.setSubject("You just got a "+gb+" review at "+date); 
        message.setText(reason);
        emailSender.send(message);
	}
}
