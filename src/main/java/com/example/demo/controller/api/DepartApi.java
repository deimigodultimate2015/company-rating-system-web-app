package com.example.demo.controller.api;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.message.SearchData;
import com.example.demo.controller.responseMessage.DepartListingResponse;
import com.example.demo.model.dao.DepartsDao;
import com.example.demo.model.entities.Departs;
import com.example.demo.model.entities.Staffs;
import com.example.demo.model.entities.custom.DepartsDetails;
import com.example.demo.model.repository.DepartRepository;
import com.example.demo.model.repository.StaffRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/depart")
public class DepartApi {

	private Logger logger = LoggerFactory.getLogger(DepartApi.class);
	
	@Autowired
	DepartRepository departRepository;
	
	@Autowired
	StaffRepository staffRepository;
	
	@Autowired
	DepartsDao departDAO;
	
	@PostMapping(value = "/add")
	public ResponseEntity<String> addDepart(@RequestBody Departs depart) {
		Departs _depart = new Departs(depart.getName());
		if(departRepository.findByName(depart.getName()).isPresent()) {
			return new ResponseEntity<String>("Add depart "+depart.getName()+" failed, this name already used!", HttpStatus.BAD_REQUEST);
		}
		departRepository.save(_depart);
			return new ResponseEntity<String>("Depart "+depart.getName()+" was created succesfully", HttpStatus.CREATED);
		
	}
	
	@PutMapping(value = "/edit")
	public ResponseEntity<String> editDepart(@RequestBody Departs depart) {
		Departs _depart = depart;
		if(!departRepository.findById(_depart.getId()).isPresent()) {
			return new ResponseEntity<String>("Edit depart failed, this id not exist", HttpStatus.BAD_GATEWAY);
		} if(departRepository.findByName(depart.getName()).isPresent()) {
			return new ResponseEntity<String>("Add depart "+depart.getName()+"failed, this name already used!", HttpStatus.BAD_REQUEST);
		}
		departRepository.save(_depart);
		return new ResponseEntity<String>("Depart "+depart.getName()+" was edited succesfully", HttpStatus.ACCEPTED);
	}
	
	@PutMapping(value = "/dismissStaff/{staffID}")
	public ResponseEntity<String> dismissStaff(@PathVariable("staffID") Long staffID) {
		Optional<Staffs> staff = staffRepository.findById(staffID);
		
		if(!staff.isPresent()) {
			return new ResponseEntity<String>("This staff isn't exist", HttpStatus.BAD_REQUEST);
		}
		
		staff.get().setDepart(null);
		staffRepository.save(staff.get());
		
		return new ResponseEntity<String>("This staff has been dismiss success", HttpStatus.OK);
	}
	
	@GetMapping(value = "/getDetails/{departID}")
	public ResponseEntity<?> getDepartDetails(@PathVariable("departID") long departID) {
		Optional<DepartsDetails> departS = departDAO.getDepartDetailsByID(departID);
		if(!departS.isPresent()) {
			return new ResponseEntity<String>("This depart doesn't exist", HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<DepartsDetails>(departS.get(), HttpStatus.OK);
	}
	
	@PutMapping(value = "/addStaff/{staffID}")
	public ResponseEntity<String> addStaff(@PathVariable("staffID") Long staffID, @RequestBody Departs depart) {
		Optional<Staffs> staffOP = staffRepository.findById(staffID);
		if(!staffOP.isPresent()) {
			return new ResponseEntity<String>("This staff isn't exist", HttpStatus.BAD_REQUEST);
		} 
		
		Staffs staff = staffOP.get();
		
		if(staff.getDepart() != null) {
			return new ResponseEntity<String>("Add failed, this staff already have department", HttpStatus.BAD_REQUEST);
		}

		if(!departRepository.findById(depart.getId()).isPresent()) {
			return new ResponseEntity<String>("Add failed, this depart isn't exist", HttpStatus.BAD_REQUEST);
		}
		
		
		if(!staff.isactive()) {
			return new ResponseEntity<String>("Add failed, this staff is disactived!", HttpStatus.BAD_REQUEST);
		}
		
		Departs _depart = departRepository.findById(depart.getId()).get();
		
		if(!_depart.isactive()) {
			return new ResponseEntity<String>("Add failed, this department is disactived!", HttpStatus.BAD_REQUEST);
		}
		
		try {
			staff.setDepart(depart);
			staffRepository.save(staff);
			return new ResponseEntity<String>("Staff was added in department successfully!", HttpStatus.ACCEPTED);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PutMapping("/disactive/{id}")
	public ResponseEntity<String> disactiveDepart(@PathVariable Long id) {
		if(!departRepository.findById(id).isPresent()) {
			return new ResponseEntity<String>("Add failed, this depart isn't exist", HttpStatus.BAD_REQUEST);
		}
		
		Departs depart = departRepository.findById(id).get();
		
		if(depart.isactive()) {
			staffRepository.updateStaffWithDepartId(id);
			depart.setactive(false);
			departRepository.save(depart);
			return new ResponseEntity<String>("Department was departed successfully!", HttpStatus.ACCEPTED);
		} else {
			depart.setactive(true);
			departRepository.save(depart);
			return new ResponseEntity<String>("Department was re-actived successfully!", HttpStatus.ACCEPTED);
		}
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getDepart(@PathVariable("id") long id) {
		Optional<Departs> _depart = departRepository.findById(id);
		if (_depart.isPresent()) {
			return new ResponseEntity<Departs>(_depart.get(), HttpStatus.FOUND);
		} else {
			return new ResponseEntity<String>("Depart "+id+" not exist" , HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/staffs/{departID}")
	public List<Staffs> getDepartStaffs(@PathVariable Long departID) {
		return departDAO.getDepartStaffs(departID);
	}
	
	@PostMapping(value = "/search") 
	public DepartListingResponse searchDepart(@RequestBody SearchData _searchData) {
		DepartListingResponse DepartListing = departDAO.searchDepartDetails(_searchData);
		return DepartListing;
	}
	
	@GetMapping("/get/total")
	public int getTotal() {
		return departRepository.findDepartsCount();
	}
	
}