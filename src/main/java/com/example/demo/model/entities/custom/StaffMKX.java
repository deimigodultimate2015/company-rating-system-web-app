package com.example.demo.model.entities.custom;

import com.example.demo.model.entities.Cloudinary;
import com.example.demo.model.entities.Staffs;

public class StaffMKX {
	private Staffs staff;
	private Cloudinary cloudinary;
	private int score;
	private int totalRating;
	private int goodRating;
	private int badRating;
	private Long departId;
	
	public StaffMKX() {
		
	}
	
	public StaffMKX(Staffs staff, int score, int totalRating, int goodRating, int badRating, Long departId) {
		super();
		this.staff = staff;
		this.score = score;
		this.totalRating = totalRating;
		this.goodRating = goodRating;
		this.badRating = badRating;
		this.departId = departId;
	}

	public Cloudinary getCloudinary() {
		return cloudinary;
	}

	public void setCloudinary(Cloudinary cloudinary) {
		this.cloudinary = cloudinary;
	}

	public Staffs getStaff() {
		return staff;
	}

	public void setStaff(Staffs staff) {
		this.staff = staff;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getTotalRating() {
		return totalRating;
	}

	public void setTotalRating(int totalRating) {
		this.totalRating = totalRating;
	}

	public int getGoodRating() {
		return goodRating;
	}

	public void setGoodRating(int goodRating) {
		this.goodRating = goodRating;
	}

	public int getBadRating() {
		return badRating;
	}

	public void setBadRating(int badRating) {
		this.badRating = badRating;
	}

	public Long getDepartId() {
		return departId;
	}

	public void setDepartId(Long departId) {
		this.departId = departId;
	}
	
	
}
