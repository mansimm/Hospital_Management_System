package com.project.hms.service.test;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.hms.entity.Address;
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
	public void init()
	{
		address = new Address(111,"xyz","abc");
		address.setAddressId(1);
		patient = new Patient(123, "Tony", 70, LocalDate.now(), TypeOfBed.GeneralWard,
				address , "Japan", "Sakura", Status.Active);
		patient.setPatientId(1);
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
	
}
