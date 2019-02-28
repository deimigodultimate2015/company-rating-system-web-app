package com.example.demo.model.dao;


import com.example.demo.controller.message.SearchData;
import com.example.demo.controller.responseMessage.RecordListingResponse;

public interface RecordsDao {
	RecordListingResponse searchRecordsByStaffId(SearchData searchData, boolean onlyStaffId);
}
