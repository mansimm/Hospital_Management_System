package com.project.hms.model;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class PatientDto {

	@NotNull(message="{PatientValidator.ssnid.null}")
	private Integer ssnId;
	@NotNull(message="{PatientValidator.name.null}")
	@Pattern(regexp="[A-Za-z]+( [A-Za-z]+)*",message="{PatientValidator.name.invalid}")
	private String name;
	@NotNull(message="{PatientValidator.age.null}")
	private Integer age;
	@NotNull(message="{PatientValidator.dateofadmission.null}")
	private LocalDate dateOfAdmission;
	@NotNull(message="{PatientValidator.typeofbed.null}")
	private TypeOfBed typeOfBed;
	
	@NotNull(message="{PatientValidator.address.null}")
	@Valid
	private AddressDto address;
	@NotNull(message="{PatientValidator.country.null}")
	private String country;
	@NotNull(message="{PatientValidator.city.null}")
	private String city;
	
	@NotNull(message="{PatientValidator.status.null}")
	private Status status;
	
	public PatientDto(Integer ssnId, String name, Integer age, LocalDate dateOfAdmission, TypeOfBed typeOfBed,
			AddressDto address, String country, String city, Status status) {
		super();
		this.ssnId = ssnId;
		this.name = name;
		this.age = age;
		this.dateOfAdmission = dateOfAdmission;
		this.typeOfBed = typeOfBed;
		this.address = address;
		this.country = country;
		this.city = city;
		this.status = status;
	}
	@Override
	public String toString() {
		return "PatientDto [ssnId=" + ssnId + ", name=" + name + ", age=" + age + ", dateOfAdmission=" + dateOfAdmission
				+ ", typeOfBed=" + typeOfBed + ", address=" + address + ", country=" + country + ", city=" + city
				+ ", status=" + status + "]";
	}
	public Integer getSsnId() {
		return ssnId;
	}
	public void setSsnId(Integer ssnId) {
		this.ssnId = ssnId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public LocalDate getDateOfAdmission() {
		return dateOfAdmission;
	}
	public void setDateOfAdmission(LocalDate dateOfAdmission) {
		this.dateOfAdmission = dateOfAdmission;
	}
	public TypeOfBed getTypeOfBed() {
		return typeOfBed;
	}
	public void setTypeOfBed(TypeOfBed typeOfBed) {
		this.typeOfBed = typeOfBed;
	}
	public AddressDto getAddress() {
		return address;
	}
	public void setAddress(AddressDto address) {
		this.address = address;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	
}
