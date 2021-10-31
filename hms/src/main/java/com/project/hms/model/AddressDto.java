package com.project.hms.model;

import javax.validation.constraints.NotNull;

public class AddressDto {
	
	private Integer addressId;
	@NotNull(message="{PatientAddressValidator.houseno.null}")
	private Integer houseNo;
	@NotNull(message="{PatientAddressValidator.area.null}")
	private String area;
	@NotNull(message="{PatientAddressValidator.street.null}")
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
