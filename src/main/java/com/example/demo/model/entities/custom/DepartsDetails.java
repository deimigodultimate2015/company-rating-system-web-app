package com.example.demo.model.entities.custom;

public class DepartsDetails {
	private Long id;
	private boolean active;
	private String name;
	private int totalRecords;
	private int goodRecords;
	private int badRecords;
	private int totalStaffs;
	private int goodStaffs;
	private int badStaffs;
	private int rating;
	
	public DepartsDetails() {
		
	}

	public DepartsDetails(Long id, boolean active, String name, int totalRecords, int goodRecords, int badRecords,
			int totalStaffs, int goodStaffs, int badStaffs, int rating) {
		super();
		this.id = id;
		this.active = active;
		this.name = name;
		this.totalRecords = totalRecords;
		this.goodRecords = goodRecords;
		this.badRecords = badRecords;
		this.totalStaffs = totalStaffs;
		this.goodStaffs = goodStaffs;
		this.badStaffs = badStaffs;
		this.rating = rating;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getGoodRecords() {
		return goodRecords;
	}

	public void setGoodRecords(int goodRecords) {
		this.goodRecords = goodRecords;
	}

	public int getBadRecords() {
		return badRecords;
	}

	public void setBadRecords(int badRecords) {
		this.badRecords = badRecords;
	}

	public int getTotalStaffs() {
		return totalStaffs;
	}

	public void setTotalStaffs(int totalStaffs) {
		this.totalStaffs = totalStaffs;
	}

	public int getGoodStaffs() {
		return goodStaffs;
	}

	public void setGoodStaffs(int goodStaffs) {
		this.goodStaffs = goodStaffs;
	}

	public int getBadStaffs() {
		return badStaffs;
	}

	public void setBadStaffs(int badStaffs) {
		this.badStaffs = badStaffs;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

}
