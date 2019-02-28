package com.example.demo.model.entities.custom;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class RecordMKII {
	private Long id;
	private boolean type;
	private String reason;
	private Long staffId;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
