package com.atlas.userservice.repository.entity;// package declaration for entity layer 

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

/**
 * Entity class represents user table in the database
 */
@Entity // Marks this class as jpa entity
@Table(name = "USER_TL") // maps entity to USER_TL table
public class UserEntity {

	@Id // marks primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment Id
	private Long userId; // Unique identification for user
	@Column(name = "FIRST_NAME", length = 50, nullable = false) // firstname can not be null and maximum leghth is 50
																// and first_name is database table column name
	private String firstName; // Firstname of the user
	@Column(name = "LAST_NAME", length = 50, nullable = false)
	private String lastName; // LastName of the user
	@Column(name = "email_id", leghth = 100, nullable = false, unique = true)
	private String emailId; // EmailID must be unique
	@Column(name = "PASSORD", length = 100, nullable = false)
	private String password;
	@Column(name = "CONTACTNUMBER", length = 10, nullable = false)
	private String contactnumber;
	@Column(name = "STATUS", length = 100, nullable = false)
	private String status;
	@Column(name = "LAST_LOGIN", nullable = false)
	private LocalDateTime lastlogin;
	@Column(name = "CREATED_AT", nullable = false, updatable = false)
	private LocalDateTime createdAt;
	@Column(name = "UPDATED_AT", nullable = true, updatable = true)
	private LocalDateTime updatedAt;
	@Column(name = "DATE_OF_BIRTH", nullable = false, updatable = false)
	private Date dateOfBirth;

	@PrePersist
	/*
	 * PrePersist method to set created at before saving
	 */
	protected void onCreate() {
		this.status = "active";// default value of the status
		this.createdAt = LocalDateTime.now(); // set current time

	}

	@PreUpdate
	/*
	 * PreUpdate method to set updated At before updating
	 */
	protected void onUpdate() {
		this.updatedAt = LocalDateTime.now();// set current time

	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContactnumber() {
		return contactnumber;
	}

	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(LocalDateTime lastlogin) {
		this.lastlogin = lastlogin;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	

}
