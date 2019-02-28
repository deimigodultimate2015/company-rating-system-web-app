package com.example.demo.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "staffs")
public class Staffs {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Nationalized
	@Column(name = "name", length = 50, nullable = false)
	@NotBlank
	@Size(max = 50, min = 3)
	private String name;
	
	@Column(name = "gender", columnDefinition = "bit", nullable = false)
	@NotNull
	private boolean gender;
	
	@Column(name = "birthday", nullable = false)
	@NotNull
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date birthday;
	
	@Column(name = "email", nullable = false, length = 254)
	@NaturalId
	@NotBlank
	@Email
	@Size(max = 254)
	private String email;
	
	@NotBlank
	@Size(min = 7, max = 15)
	@NaturalId
	@Column(name = "phone", nullable = false, length = 15)
	private String phone;
	
	@Column(name = "salary", nullable = false)
	private long salary  ;

	@Nationalized
	@Column(name = "notes", nullable = true, length = 1000)
	@Size(max = 1000)
	private String notes;
	
	@Column(name = "level", nullable = false)
	@NotNull
	@Min(0)
	@Max(10)
	private byte level = 0;
	
	@NotNull
	@Column(name = "active", nullable = false)
	private boolean active;
	
	@ManyToOne()
	@JoinColumn(name = "departs_id", nullable = true)
	private Departs depart;
	
	@JsonIgnore
	@OneToMany(mappedBy = "staff", fetch = FetchType.LAZY)
	private Set<Records> records = new HashSet<>();

	public Staffs () { this.active = true; };

	public Staffs(Long id, @NotBlank @Size(max = 50, min = 3) String name, @NotNull boolean gender,
			@NotNull Date birthday, @NotBlank @Email @Size(max = 254) String email,
			@NotBlank @Size(min = 7, max = 15) String phone, long salary, @Size(max = 1000) String notes,
			@NotNull @Min(0) @Max(10) byte level, @NotNull boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.email = email;
		this.phone = phone;
		this.salary = salary;
		this.notes = notes;
		this.level = level;
		this.active = active;
	}

	public Staffs(@NotBlank @Size(max = 50, min = 3) String name, @NotNull boolean gender,
			@NotNull Date birthday, @NotBlank @Email @Size(max = 254) String email,
			@NotBlank @Size(min = 7, max = 15) String phone, long salary, @Size(max = 1000) String notes,
			@NotNull @Min(0) @Max(10) byte level, @NotNull boolean active, Departs depart, Set<Records> records) {
		super();
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.email = email;
		this.phone = phone;
		this.salary = salary;
		this.notes = notes;
		this.level = level;
		this.active = active;
		this.depart = depart;
		this.records = records;
	}

	public byte getLevel() {
		return level;
	}

	public void setLevel(byte level) {
		this.level = level;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public boolean isactive() {
		return active;
	}

	public void setactive(boolean active) {
		this.active = active;
	}

	public Departs getDepart() {
		return depart;
	}

	public void setDepart(Departs depart) {
		this.depart = depart;
	}

	public Set<Records> getRecords() {
		return records;
	}

	public void setRecords(Set<Records> records) {
		this.records = records;
	}

	public Long getId() {
		return id;
	} 
	
	
}
