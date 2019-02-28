package com.example.demo.controller.responseMessage;

import java.util.List;

import com.example.demo.model.entities.custom.DepartsDetails;
import com.example.demo.model.entities.custom.PageMKII;

public class DepartListingResponse {
	List<DepartsDetails> depart;
	PageMKII pageinf;
	
	public DepartListingResponse(List<DepartsDetails> depart, PageMKII pageinf) {
		super();
		this.depart = depart;
		this.pageinf = pageinf;
	}
	
	public List<DepartsDetails> getDepart() {
		return depart;
	}
	public void setDepart(List<DepartsDetails> depart) {
		this.depart = depart;
	}
	public PageMKII getPageinf() {
		return pageinf;
	}
	public void setPageinf(PageMKII pageinf) {
		this.pageinf = pageinf;
	}
	
	
	
	
}
