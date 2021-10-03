package com.project.hms.model;

import java.time.LocalDateTime;

public class HospitalStaff {

	private String fname;
	private String lname;
	private String email;
	private Long contact;
	private Long addharNumber;
	private String userName;
	private String password;
	private LocalDateTime timestamp;
	
	public HospitalStaff(String fname, String lname, String email, Long contact, Long addharNumber, String userName,
			String password, LocalDateTime timestamp) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.contact = contact;
		this.addharNumber = addharNumber;
		this.userName = userName;
		this.password = password;
		this.timestamp = timestamp;
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
