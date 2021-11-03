package com.project.hms.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hms.entity.Bill;
import com.project.hms.entity.DiagnosticsConducted;
import com.project.hms.entity.Medicine;
import com.project.hms.entity.MedicineIssued;
import com.project.hms.entity.Patient;
import com.project.hms.exception.BillingServiceException;
import com.project.hms.model.BillDto;
import com.project.hms.model.TypeOfBed;
import com.project.hms.repository.DiagnosisRepo;
import com.project.hms.repository.DiagnosticsConductedRepo;
import com.project.hms.repository.MedicineIssuedRepo;
import com.project.hms.repository.MedicineRepo;
import com.project.hms.repository.PatientRepo;

@Service
@Transactional
public class BillingServiceImpl implements BillingService{

	@Autowired
	MedicineRepo medicineRepo;
	@Autowired
	MedicineIssuedRepo medicineIssuedRepo;
	@Autowired
	DiagnosticsConductedRepo diagnosticsConductedRepo;
	@Autowired
	DiagnosisRepo diagnosisRepo;
	@Autowired
	PatientRepo patientRepo;
	@Override
	public BillDto calculateTotalBill(Integer patientId) throws BillingServiceException {
		Optional<Patient> op = patientRepo.findById(patientId);
		Patient p = op.orElseThrow(()->new BillingServiceException("Patient not found"));
		
		Bill bill = new Bill();
		Double medBill = calculateMedicineBill(patientId);
		Double diaBill = calculateDiagnisisBill(patientId);
		Double roomBill = calculateRoomBill(patientId);
		Double gst = applyGST(roomBill);
		Double totalBill = medBill + diaBill + roomBill + gst;
		
		bill.setMedicineBill(medBill);
		bill.setDiagnisisBill(diaBill);
		bill.setRoomBill(roomBill);
		bill.setGst(gst);
		bill.setTotalBill(totalBill);
		
		p.setBill(bill);
		
		return entityToDto(bill);
	}

	@Override
	public double calculateMedicineBill(Integer patientId) {
		List<MedicineIssued> medIssueList = medicineIssuedRepo.findByPatientId(patientId);
		Double bill = 0.0;
		for(MedicineIssued i: medIssueList)
		{
			Double price = medicineRepo.findRateByMedicineId(i.getMedicineId());
			bill += price*i.getQuantity();
		}
		return bill;
	}

	@Override
	public double calculateDiagnisisBill(Integer patientId) {
		List<DiagnosticsConducted> diaList = diagnosticsConductedRepo.findByPatientId(patientId);
		Double bill =0.0;
		for(DiagnosticsConducted d: diaList)
		{
			bill += diagnosisRepo.findTestChargesByTestId(d.getTestId());
		}
		return bill;
	}

	@Override
	public double calculateRoomBill(Integer patientId) throws BillingServiceException {
		Optional<Patient> op = patientRepo.findById(patientId);
		Patient p = op.orElseThrow(()->new BillingServiceException("Patient not found"));
		Double rentPerDay = 0.0;
		if(p.getTypeOfBed().equals(TypeOfBed.GeneralWard))
		{
			rentPerDay = 2000.0;
		}
		else if(p.getTypeOfBed().equals(TypeOfBed.SemiSharing))
		{
			rentPerDay = 4000.0;
		}
		else 
		{
			rentPerDay = 8000.0;
		}
		int noOfDays = noOfDaysInHospital(patientId);
		return noOfDays*rentPerDay;
	}

	public int noOfDaysInHospital(Integer patientId) throws BillingServiceException
	{
		Optional<Patient> op = patientRepo.findById(patientId);
		Patient p = op.orElseThrow(()->new BillingServiceException("Patient not found"));
		
		if(p.getDateOfDischarge()==null)
		{
			p.setDateOfDischarge(LocalDate.now());
		}
		else if(p.getDateOfAdmission().equals(p.getDateOfDischarge()))
		{
			return 1;
		}
		return	p.getDateOfDischarge().compareTo(p.getDateOfAdmission());		
	}
	
	public Double applyGST(Double roomRent)
	{
		Double gst = 0.0;
		Integer rate = 0;
		//Below Rs.1000	0%
		//Rs.1000 to Rs.2499	12%
		//Rs.2500 to Rs.7499	18%
		//Above Rs.7500	28%
		if(roomRent<1000.0)
		{
			rate = 0;
		}
		else if(roomRent>=1000.0 && roomRent<=2499)
		{
			rate = 12;
		}
		else if(roomRent>=2500.0 && roomRent<=7499)
		{
			rate = 18;
		}
		else
		{
			rate = 28;
		}
		gst = roomRent*rate/100.0;
		return gst;
	}
	
	public BillDto entityToDto(Bill bill)
	{
		BillDto billDto = new BillDto(bill.getMedicineBill(),bill.getDiagnisisBill(),bill.getRoomBill(),
				bill.getGst(),bill.getTotalBill(),bill.getPaymentMode(),bill.getTransactionId());
		return billDto;
	}
}
