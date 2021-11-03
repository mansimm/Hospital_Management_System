package com.project.hms.service.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.hms.entity.Address;
import com.project.hms.entity.Diagnosis;
import com.project.hms.entity.DiagnosticsConducted;
import com.project.hms.entity.Patient;
import com.project.hms.exception.BillingServiceException;
import com.project.hms.model.Status;
import com.project.hms.model.TypeOfBed;
import com.project.hms.repository.DiagnosisRepo;
import com.project.hms.repository.DiagnosticsConductedRepo;
import com.project.hms.repository.MedicineIssuedRepo;
import com.project.hms.repository.MedicineRepo;
import com.project.hms.repository.PatientRepo;
import com.project.hms.service.BillingServiceImpl;


@SpringBootTest
public class BillingServiceImplTest {
	
	@Mock
	MedicineRepo medicineRepo;
	@Mock
	MedicineIssuedRepo medicineIssuedRepo;
	@Mock
	DiagnosticsConductedRepo diagnosticsConductedRepo;
	@Mock
	DiagnosisRepo diagnosisRepo;
	@Mock
	PatientRepo patientRepo;
	@InjectMocks
	BillingServiceImpl billingServiceImpl = new BillingServiceImpl();
	
	Patient patient;
	Address address;
	Diagnosis diagnosis;
	DiagnosticsConducted diagnosticsConducted;
	public void init()
	{
		address = new Address(111,"xyz","abc");
		address.setAddressId(1);
		patient = new Patient(123, "Tony", 70, LocalDate.now(), TypeOfBed.GeneralWard,
				address , "Japan", "Sakura", Status.Active);
		patient.setPatientId(1);
		diagnosis = new Diagnosis("Hemogram","Hemogram comprises of CBC and ESR",150.0);
		diagnosis.setTestId(1);
		diagnosticsConducted = new DiagnosticsConducted(1,  1,"Positive", "Need iron rich diet");
		diagnosticsConducted.setDiagnosticsConductedId(1);		

	}
	@Test
	public void noOfDaysInHospitalOneDayTest() throws BillingServiceException
	{
		init();
		patient.setDateOfAdmission(LocalDate.now());
		patient.setDateOfDischarge(LocalDate.now());
		Mockito.when(patientRepo.findById(1)).thenReturn(Optional.of(patient));
		Integer days = billingServiceImpl.noOfDaysInHospital(1);
		Assertions.assertEquals(1, days);
	}
	@Test
	public void noOfDaysInHospitalMoreDaysTest() throws BillingServiceException
	{
		init();
		patient.setDateOfAdmission(LocalDate.now());
		patient.setDateOfDischarge(LocalDate.now().plusDays(5));
		Mockito.when(patientRepo.findById(1)).thenReturn(Optional.of(patient));
		Integer days = billingServiceImpl.noOfDaysInHospital(1);
		Assertions.assertEquals(5, days);
	}
	@Test
	public void applyGSTTest1()
	{
		Double gst = billingServiceImpl.applyGST(500.0);
		Assertions.assertEquals(0.0, gst);
	}
	@Test
	public void applyGSTTest2()
	{
		Double gst = billingServiceImpl.applyGST(5500.0);
		Double required = 5500.0*18/100.0;
		Assertions.assertEquals(required, gst);
	}
	@Test
	public void calculateRoomBillGeneralWard() throws BillingServiceException
	{
		init();
		patient.setDateOfAdmission(LocalDate.now());
		patient.setDateOfDischarge(LocalDate.now().plusDays(5));
		Mockito.when(patientRepo.findById(1)).thenReturn(Optional.of(patient));
		Double bill = billingServiceImpl.calculateRoomBill(1);//2000.0/day
		Assertions.assertEquals(2000.0*5, bill);
	}
	@Test
	public void calculateRoomBillSemiSharing() throws BillingServiceException
	{
		init();
		patient.setDateOfAdmission(LocalDate.now());
		patient.setDateOfDischarge(LocalDate.now().plusDays(5));
		patient.setTypeOfBed(TypeOfBed.SemiSharing);
		Mockito.when(patientRepo.findById(1)).thenReturn(Optional.of(patient));
		Double bill = billingServiceImpl.calculateRoomBill(1);//4000.0/day
		Assertions.assertEquals(4000.0*5, bill);
	}
	@Test
	public void calculateRoomBillSingleRoom() throws BillingServiceException
	{
		init();
		patient.setDateOfAdmission(LocalDate.now());
		patient.setDateOfDischarge(LocalDate.now().plusDays(5));
		patient.setTypeOfBed(TypeOfBed.SingleRoom);
		Mockito.when(patientRepo.findById(1)).thenReturn(Optional.of(patient));
		Double bill = billingServiceImpl.calculateRoomBill(1);//8000.0/day
		Assertions.assertEquals(8000.0*5, bill);
	}
	
	@Test
	public void calculateDiagnisisBillTest1()
	{
		init();
		Mockito.when(diagnosticsConductedRepo.findByPatientId(1)).thenReturn(List.of(diagnosticsConducted));
		Mockito.when(diagnosisRepo.findTestChargesByTestId(1)).thenReturn(150.0);
		Double bill = billingServiceImpl.calculateDiagnisisBill(1);
		Assertions.assertEquals(150.0, bill);
	}
	@Test
	public void calculateDiagnisisBillTest2()
	{
		init();
		Mockito.when(diagnosticsConductedRepo.findByPatientId(1)).thenReturn(new ArrayList());
		//Mockito.when(diagnosisRepo.findTestChargesByTestId(1)).thenReturn(150.0);
		Double bill = billingServiceImpl.calculateDiagnisisBill(1);
		Assertions.assertEquals(0.0, bill);
	}
	
}
