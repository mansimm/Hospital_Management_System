package com.project.hms.model;

public class AddressDto {
	
	private Integer addressId;
	private Integer houseNo;
	private String area;
	private String street;
	
	public AddressDto(Integer houseNo, String area, String street) {
		super();
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
