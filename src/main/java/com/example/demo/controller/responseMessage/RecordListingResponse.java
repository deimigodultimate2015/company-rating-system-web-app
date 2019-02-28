package com.example.demo.controller.responseMessage;

import java.util.List;

import com.example.demo.model.entities.custom.PageMKII;
import com.example.demo.model.entities.custom.RecordMKII;

public class RecordListingResponse {
	List<RecordMKII> records;
	PageMKII page;
	
	public RecordListingResponse() {}

	public RecordListingResponse(List<RecordMKII> records, PageMKII page) {
		super();
		this.records = records;
		this.page = page;
	}

	public List<RecordMKII> getRecords() {
		return records;
	}

	public void setRecords(List<RecordMKII> records) {
		this.records = records;
	}

	public PageMKII getPage() {
		return page;
	}

	public void setPage(PageMKII page) {
		this.page = page;
	}
	
	
	
	
}
