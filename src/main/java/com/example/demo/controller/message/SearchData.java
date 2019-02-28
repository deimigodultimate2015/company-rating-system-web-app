package com.example.demo.controller.message;

public class SearchData {
	private String search;
	private int page;
	private int size;
	private String sort;
	
	public SearchData(String search, int page, int size, String sort) {
		super();
		this.search = search;
		this.page = page;
		this.size = size;
		this.sort = sort;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	
	
	
}
