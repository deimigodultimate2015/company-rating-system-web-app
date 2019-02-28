package com.example.demo.model.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Nationalized;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "record")
public class Records implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4990358093423044085L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name = "type", nullable = false)
	private boolean type;
	
	@Nationalized
	@NotBlank
	@Size(min = 15, max = 1000)
	@Column(name = "reason", length = 1000)
	private String reason;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	@Column(name = "date", nullable = false)
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date date;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "staff_id", nullable = false)
	@JsonBackReference
	private Staffs staff;
	
	public Records() {}
	
	public Records(@NotNull boolean type, @NotBlank @Size(min = 15, max = 1000) String reason, @NotNull Date date) {
		super();
		this.type = type;
		this.reason = reason;
		this.date = date;
	}

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

	public Staffs getStaff() {
		return staff;
	}

	public void setStaff(Staffs staff) {
		this.staff = staff;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
