package com.project.hms.service;

import com.project.hms.exception.BillingServiceException;
import com.project.hms.model.BillDto;

public interface BillingService {

	public BillDto calculateTotalBill(Integer patientId) throws BillingServiceException;
	public double calculateMedicineBill(Integer patientId);
	public double calculateDiagnisisBill(Integer patientId);
	public double calculateRoomBill(Integer patientId) throws BillingServiceException;
}
