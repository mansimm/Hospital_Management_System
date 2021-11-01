package com.project.hms.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class HospitalStaffDto {

	private Integer staffId;
	@NotNull(message="{HospitalStaffDto.fname.null}")
	private String fname;
	@NotNull(message="{HospitalStaffDto.lname.null}")
	private String lname;
	
	@NotNull(message="{HospitalStaffDto.email.null}")
	@Pattern(regexp="[A-Za-z0-9]+@([A-Za-z]+).(com|in)",message="{HospitalStaffDto.email.invalid}")
	private String email;
	@NotNull(message="{HospitalStaffDto.contact.null}")
	private Long contact;
	@NotNull(message="{HospitalStaffDto.addharNumber.null}")
	private Long addharNumber;
	
	@NotNull(message="{HospitalStaffDto.userName.null}")
	@Pattern(regexp="[A-Za-z0-9!@#$%^&*]{7,20}",
	message="{HospitalStaffDto.uname.invalid}")
	private String userName;
	
	@NotNull(message="{HospitalStaffDto.password.null}")
	@Pattern(regexp="(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{7,20}",
	message="{HospitalStaffDto.password.invalid}")
	private String password;
	private LocalDateTime timestamp;
	
	public HospitalStaffDto(String fname, String lname, String email, Long contact, Long addharNumber, String userName,
			String password, LocalDateTime timestamp) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.contact = contact;
		this.addharNumber = addharNumber;
		this.userName = userName;
		this.password = password;
		this.timestamp = LocalDateTime.now();
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getContact() {
		return contact;
	}

	public void setContact(Long contact) {
		this.contact = contact;
	}

	public Long getAddharNumber() {
		return addharNumber;
	}

	public void setAddharNumber(Long addharNumber) {
		this.addharNumber = addharNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
