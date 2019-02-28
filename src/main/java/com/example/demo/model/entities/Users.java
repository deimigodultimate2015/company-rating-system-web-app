package com.example.demo.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "users")
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "email", nullable = false, length =254)
	@Email
	@NaturalId
	@NotBlank
	private String email;
	
	@Column(name = "username", nullable = false, length = 50, unique = true)
	@NotBlank
	@Size(max = 50, min = 3)
	private String username;
	
	@NotBlank
	@Column(name = "password", nullable = false, length = 100)
	@Size(max = 100, min = 6)
	private String password;
	
	@Nationalized
	@Column(name = "fullname", nullable = false, length = 50)
	@NotBlank
	@Size(max = 50, min = 3)
	private String fullname;
	
	@NotNull
	@Column(name = "active", nullable = false)
	private boolean active;

	public Users() {
		this.active =true;
	}
	
	public Users(Long id, @Email @NotBlank String email, @NotBlank @Size(max = 50, min = 3) String username,
			 @NotBlank @Size(max = 100, min = 6) String password, @NotBlank @Size(max = 50, min = 3) String fullname) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.active = true;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
}
