package com.example.demo.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cloudinary")
public class Cloudinary {
	
	@Id
	private Long staffId;
	
	@Column(name = "version", length = 12, nullable = false)
	@Size(min = 9, max = 12)
	@NotBlank
	private String version;
	
	@Column(name = "type", length = 3, nullable = false)
	@Size(min = 3, max = 3)
	@NotBlank
	private String type;
	
	public Cloudinary() {}
	
	public Cloudinary(Long staffId, @Size(min = 9, max = 12) @NotBlank String version,
			@Size(min = 3, max = 3) @NotBlank String type) {
		super();
		this.staffId = staffId;
		this.version = version;
		this.type = type;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}
	
	
	
}
