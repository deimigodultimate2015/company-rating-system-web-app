package com.example.demo.controller.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

import com.example.demo.controller.cloudinary.CloudinaryUtils;
import com.example.demo.controller.message.SearchData;
import com.example.demo.model.dao.StaffMKXDao;
import com.example.demo.model.dao.impl.utils.SearchDataChecker;
import com.example.demo.model.entities.Staffs;
import com.example.demo.model.entities.custom.StaffMKX;
import com.example.demo.model.repository.CloudinaryRepository;
import com.example.demo.model.repository.StaffRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/staff")
public class StaffApi {
	
	@Autowired
	CloudinaryUtils cloudinaryUtils;
	
	@Autowired
	StaffRepository staffRepository;
	
	@Autowired
	StaffMKXDao staffMKXDao;
	
	@Autowired
	SearchDataChecker searchDataChecker;
	
	@Autowired
	CloudinaryRepository cloudinaryRepository;
	
	@GetMapping("/getTop10")
	public List<StaffMKX> getTop10Staff() {
		return staffMKXDao.getTop10Staff();	
	}
	
	@PostMapping(value = "/add")
	public ResponseEntity<String> addStaff(@RequestBody Staffs staff) {
		staff.setId(null);
		if(staffRepository.existsByEmail(staff.getEmail())) {
			return new ResponseEntity<String>("This email already used", HttpStatus.BAD_REQUEST);
		}
		
		if(staffRepository.existsByPhone(staff.getPhone())) {
			return new ResponseEntity<String>("This phone already used", HttpStatus.BAD_REQUEST);
		}
		
		Staffs _staff = new Staffs(
				staff.getName(),
				staff.isGender(),
				staff.getBirthday(),
				staff.getEmail(),
				staff.getPhone(),
				staff.getSalary(),
				staff.getNotes(),
				staff.getLevel(),
				true,
				null,
				null
				);
		staffRepository.save(_staff);
		return new ResponseEntity<String>("This staff was added successful", HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/update")
	public ResponseEntity<String> updateStaff(@RequestBody Staffs staff) {
		if(!staffRepository.existsById(staff.getId())) {
			return new ResponseEntity<String>("This staff un-existed!", HttpStatus.BAD_REQUEST); 
		}
		staffRepository.save(staff);
		return new ResponseEntity<String>("Update staff information success!", HttpStatus.CREATED); 
	}
	
	@PutMapping(value = "/disactive/{staffId}")
	public ResponseEntity<String> disactiveStaff(@PathVariable("staffId") Long id) {
		Optional<Staffs> staff = staffRepository.findById(id);
		if(!staff.isPresent()) return new ResponseEntity<String>("This staff un-existed!", HttpStatus.BAD_REQUEST); 
		
		Staffs _staff = staff.get();
		if(_staff.isactive()) {
			_staff.setactive(false);
		} else {
			_staff.setactive(true);
		}
		
		staffRepository.save(_staff);
		return new ResponseEntity<String>("Update staff information success!", HttpStatus.CREATED); 
	}
	
	@PostMapping(value = "/search")
	public Page<Staffs> searchStaff(@RequestBody SearchData searchData) {
		String[] keyWords = {"name","level", "id"};
		SearchData _searchData = searchDataChecker.SearchDataCheckerSortingFix(searchData,keyWords, "name");
		Pageable page = PageRequest.of(_searchData.getPage(), _searchData.getSize(), Sort.by(_searchData.getSort()).ascending());
		return staffRepository.findBy3Column("%"+_searchData.getSearch()+"%", page);
	}
	
	@GetMapping("/get/{staffId}")
	public Staffs getStaffById(@PathVariable("staffId") Long id) {
		return staffRepository.findById(id).get();
	}
	
}
