package com.project.hms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer addressId;
	private Integer houseNo;
	private String area;
	private String street;
	
	public Address() {}
	
	public Address(Integer addressId, Integer houseNo, String area, String street) {
		super();
		this.addressId = addressId;
		this.houseNo = houseNo;
		this.area = area;
		this.street = street;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public Integer getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(Integer houseNo) {
		this.houseNo = houseNo;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	
	
	
}
