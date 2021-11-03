package com.project.hms.model;



public class BillDto {
	
	private Integer billId;
	private Double medicineBill;
	private Double diagnisisBill;
	private Double roomBill;
	private Double gst;
	private Double totalBill;
	private PaymentMode paymentMode;
	private String transactionId;
	
	public BillDto(Double medicineBill, Double diagnisisBill, Double roomBill, Double gst, Double totalBill,
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
