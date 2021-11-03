package com.project.hms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.project.hms.model.PaymentMode;

@Entity
@Table(name="bill")
public class Bill {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer billId;
	private Double medicineBill;
	private Double diagnisisBill;
	private Double roomBill;
	private Double gst;
	private Double totalBill;
	private PaymentMode paymentMode;
	private String transactionId;
	
	public Bill(Double medicineBill, Double diagnisisBill, Double roomBill, Double gst, Double totalBill,
			PaymentMode paymentMode, String transactionId) {
		super();
		this.medicineBill = medicineBill;
		this.diagnisisBill = diagnisisBill;
		this.roomBill = roomBill;
		this.gst = gst;
		this.totalBill = totalBill;
		this.paymentMode = paymentMode;
		this.transactionId = transactionId;
	}

	public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	public Double getMedicineBill() {
		return medicineBill;
	}

	public void setMedicineBill(Double medicineBill) {
		this.medicineBill = medicineBill;
	}

	public Double getDiagnisisBill() {
		return diagnisisBill;
	}

	public void setDiagnisisBill(Double diagnisisBill) {
		this.diagnisisBill = diagnisisBill;
	}

	public Double getRoomBill() {
		return roomBill;
	}

	public void setRoomBill(Double roomBill) {
		this.roomBill = roomBill;
	}

	public Double getGst() {
		return gst;
	}

	public void setGst(Double gst) {
		this.gst = gst;
	}

	public Double getTotalBill() {
		return totalBill;
	}

	public void setTotalBill(Double totalBill) {
		this.totalBill = totalBill;
	}

	public PaymentMode getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(PaymentMode paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	
	
}
