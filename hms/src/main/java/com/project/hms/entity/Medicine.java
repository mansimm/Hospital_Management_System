package com.project.hms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="medicines")
public class Medicine {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer medicineId;
	private String medicineName;
	private Integer quantity;
	private Double rate;
	
	public Medicine() {}
	
	public Medicine(String medicineName, Integer quantity, Double rate) {
		super();
		this.medicineName = medicineName;
		this.quantity = quantity;
		this.rate = rate;
	}

	public Integer getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(Integer medicineId) {
		this.medicineId = medicineId;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}
	
	
}
