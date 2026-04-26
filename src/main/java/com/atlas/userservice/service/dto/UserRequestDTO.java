package com.atlas.userservice.service.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserRequestDTO {

	@NotBlank(message = "firstName is required") // Must not be null or empty
	@Size(min = 3, max = 50, message = "firstName must be beteen 3 and 50 characters")
	private String firstName;// first name of the user

	@NotBlank(message = "lastNAme is required") // Must not be null or empty
	@Size(min = 3, max = 50, message = "lastName must be beteen 3 and 50 characters")
	private String lastName; // lastName of user

	@NotBlank(message = "emailId is required") // must not be null or empty
	@Email(message = "invalid email formate") //
	private String emailId;// emailId of

	@NotBlank(message = "passord is required") // must not be empty
	@Size(min = 8, max = 20, message = "passord must be 8 to 20 characters")
	@Pattern(
		    regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).*$",
		    message = "Password must contain uppercase, lowercase, number, and special character"
		)
	private String password;

	@NotBlank(message = "contactNumber is required")
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid phone number") // validates
	private String contactNumber;

	@NotNull(message = "date of birth is required")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;

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

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

}
