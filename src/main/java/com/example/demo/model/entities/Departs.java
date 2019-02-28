package com.example.demo.model.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Nationalized;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "departs")
public class Departs {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Nationalized
	@Column(name = "name", length = 80, nullable = false, unique = true)
	@NotBlank
	@Size(max = 80, min = 15)
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy = "depart", fetch = FetchType.LAZY)
	private Set<Staffs> staffs = new HashSet<>();
	
	@NotNull
	@Column(name = "active", nullable = false)
	private boolean active;

	public Departs() {
		this.active = true;
	}
	
	public Departs(@NotBlank @Size(max = 80, min = 15) String name) {
		super();
		this.name = name;
		this.active = true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Staffs> getStaffs() {
		return staffs;
	}

	public void setStaffs(Set<Staffs> staffs) {
		this.staffs = staffs;
	}

	public boolean isactive() {
		return active;
	}

	public void setactive(boolean active) {
		this.active = active;
	}

	public Long getId() {
		return id;
	}
	
}
