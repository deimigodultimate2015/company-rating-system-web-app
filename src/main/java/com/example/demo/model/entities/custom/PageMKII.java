package com.example.demo.model.entities.custom;

public class PageMKII {
	private int currentPage;
	private int pageSize;
	private int totalEntity;
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalEntity() {
		return totalEntity;
	}
	public void setTotalEntity(int totalEntity) {
		this.totalEntity = totalEntity;
	}

}
