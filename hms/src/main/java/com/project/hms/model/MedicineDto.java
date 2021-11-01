package com.project.hms.model;

public class MedicineDto {
	
	private Integer medicineId;
	private String medicineName;
	private Integer quantity;
	private Double rate;
	
	public MedicineDto(String medicineName, Integer quantity, Double rate) {
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
