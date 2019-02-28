package com.example.demo.model.dao;

import java.util.List;
import java.util.Optional;

import com.example.demo.controller.message.SearchData;
import com.example.demo.controller.responseMessage.DepartListingResponse;
import com.example.demo.model.entities.Staffs;
import com.example.demo.model.entities.custom.DepartsDetails;

public interface DepartsDao {
	
	DepartListingResponse searchDepartDetails(SearchData searchData);
	
	Optional<DepartsDetails> getDepartDetailsByID(Long departID);

	List<Staffs> getDepartStaffs(Long id);
	
}
