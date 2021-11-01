package com.project.hms.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.project.hms.model.TypeOfBed;
import com.project.hms.model.Status;

@Entity
public class Patient {

	
	private Integer ssnId;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer patientId;
	private String name;
	private Integer age;
	private LocalDate dateOfAdmission;
	
	@Enumerated(EnumType.STRING)
	private TypeOfBed typeOfBed;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name= "address_id",unique=true)
	private Address address;
	private String country;
	private String city;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="medicine_issued_id")
	private List<MedicineIssued> medicineIssued;
	
	public Patient(){}
	
	public Patient(Integer ssnId, String name, Integer age, LocalDate dateOfAdmission, TypeOfBed typeOfBed,
			Address address, String country, String city, Status status) {
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
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
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

	public List<MedicineIssued> getMedicineIssued() {
		return medicineIssued;
	}

	public void setMedicineIssued(List<MedicineIssued> medicineIssued) {
		this.medicineIssued = medicineIssued;
	}
	
	
}
