package com.mojdan.app.model.user;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Size(max = 20)
	private String username;

	@Size(max = 120)
	private String password;

	@Size(max = 50)
	@Email
	private String email;

	private String phonenumber;

	@Enumerated(EnumType.STRING)
	private Status status;

	private String resetPassToken;

	private Date dateExpPassToken;

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Role> roles = new HashSet<>();

	public User() {
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User(@NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 20) String username,
			@NotBlank @Size(max = 20) String password, String phonenumber, Status status, Role role) {
		this.email = email;
		this.username = username;
		this.password = password;
		this.phonenumber = phonenumber;
		this.status = status;
		this.roles = new HashSet<Role>();
		this.roles.add(role);
	}

	public User(@NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
			@NotBlank @Size(max = 120) String password) {
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getResetPassToken() {
		return resetPassToken;
	}

	public void setResetPassToken(String resetPassToken) {
		this.resetPassToken = resetPassToken;
	}

	public Date getDateExpPassToken() {
		return dateExpPassToken;
	}

	public void setDateExpPassToken(Date dateExpPassToken) {
		this.dateExpPassToken = dateExpPassToken;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void addRole(Role role) {
		if (roles.isEmpty()) {
			roles = new HashSet<Role>();
		}
		roles.add(role);
	}

}
