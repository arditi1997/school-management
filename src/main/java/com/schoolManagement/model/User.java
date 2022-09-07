package com.schoolManagement.model;

// @formatter:on

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity(name = "User")
@Table(name = "auth_user")
public class User {
	@Id
	@GeneratedValue
	@Column(name = "auth_user_id")
	private int userId;
	@Column(name = "name")
	private String name;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "username")
	private String username;
	@Email(message = "Email is invalid")
	@Column(name = "email")
	private String email;
	@Column(name = "gender")
	private String gender;
	@Column(name = "nationality")
	private String nationality;
	@Column(name = "address")
	private String address;
	@Column(name = "addmissionYear")
	private String addmissionYear;
	@Column(name = "title")
	private String title;
	@Column(name = "password")
	private String password;
	@Column(name = "birthdate")
	private String birthdate;
	@Column(name = "status")
	private String status;
	
	@Column(name = "reset_token")
	private String resetToken;

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "auth_user_role", joinColumns = @JoinColumn(name = "auth_user_id"), inverseJoinColumns = @JoinColumn(name = "auth_role_id"))
	private Set<Role> roles;

	@ManyToOne
	@JoinColumn
	private Classes classes;

	@ManyToOne
	@JoinColumn
	private User parentOfStudent;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getParentOfStudent() {
		return parentOfStudent;
	}
	
	public void setParentOfStudent(User parentOfStudent) {
		this.parentOfStudent = parentOfStudent;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddmissionYear() {
		return addmissionYear;
	}

	public void setAddmissionYear(String addmissionYear) {
		this.addmissionYear = addmissionYear;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public Classes getClasses() {
		return classes;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}

	public User(String name) {
		this.name = name;
	}

	public int getUserId() {
		return userId;
	}

	public User() {
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}