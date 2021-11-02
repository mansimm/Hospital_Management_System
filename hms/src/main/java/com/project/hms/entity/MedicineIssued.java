package com.project.hms.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="medicine_issued")
public class MedicineIssued {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="medicine_issued_id")
	private Integer medicineIssuedId;
	private Integer patientId;
	private Integer medicineId;
	private Integer quantity;
	private LocalDate dateOfIssue;
	
	public MedicineIssued()
	{}
	public MedicineIssued( Integer patientId, Integer medicineId, Integer quantity) {
	
		this.patientId = patientId;
		this.medicineId = medicineId;
		this.quantity = quantity;
		this.dateOfIssue = LocalDate.now();
	}

	public Integer getMedicineIssuedId() {
		return medicineIssuedId;
	}

	public void setMedicineIssuedId(Integer medicineIssuedId) {
		this.medicineIssuedId = medicineIssuedId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(Integer medicineId) {
		this.medicineId = medicineId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public LocalDate getDateOfIssue() {
		return dateOfIssue;
	}
	public void setDateOfIssue(LocalDate dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}
	
	
}
