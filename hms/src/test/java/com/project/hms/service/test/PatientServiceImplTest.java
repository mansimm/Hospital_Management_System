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
import com.project.hms.entity.Medicine;
import com.project.hms.entity.Patient;
import com.project.hms.exception.DiagnosticsServiceException;
import com.project.hms.exception.PatientServiceException;
import com.project.hms.model.AddressDto;
import com.project.hms.model.PatientDto;
import com.project.hms.model.Status;
import com.project.hms.model.TypeOfBed;
import com.project.hms.repository.DiagnosisRepo;
import com.project.hms.repository.DiagnosticsConductedRepo;
import com.project.hms.repository.MedicineIssuedRepo;
import com.project.hms.repository.MedicineRepo;
import com.project.hms.repository.PatientRepo;
import com.project.hms.service.PatientServiceImpl;

@SpringBootTest
public class PatientServiceImplTest {

	@Mock
	PatientRepo patientRepo;
	@Mock
	MedicineRepo medicineRepo;
	@Mock
	DiagnosisRepo diagnosisRepo;
	@Mock
	DiagnosticsConductedRepo diagnosticsConductedRepo;
	@Mock
	MedicineIssuedRepo medicineIssuedRepo;
	@InjectMocks
	PatientServiceImpl patientServiceImpl= new PatientServiceImpl();
	
	AddressDto addressDto;
	PatientDto patientDto1;
	Patient patient;
	Address address;
	Medicine medicine;
	Diagnosis diagnosis;
	
	public void init()
	{
		addressDto = new AddressDto(111,"xyz","abc");
		address = new Address(111,"xyz","abc");
		patientDto1 = new PatientDto(123, "Tony", 70, LocalDate.now(), TypeOfBed.GeneralWard,
				addressDto , "Japan", "Sakura", Status.Active);//already present
		patient = new Patient(123, "Tony", 70, LocalDate.now(), TypeOfBed.GeneralWard,
				address , "Japan", "Sakura", Status.Active);
		patient.setPatientId(1);
		medicine = new Medicine("Crosine",20,10.0);
		medicine.setMedicineId(1);
		
		diagnosis = new Diagnosis("Hemogram","Blood count",150.0);
		diagnosis.setTestId(1);
		
	}
	@Test
	public void addPatientSuccessTest() throws PatientServiceException
	{
		init();
		Mockito.when(patientRepo.findBySsnId(patientDto1.getSsnId())).thenReturn(Optional.ofNullable(null));
		Patient p = patientServiceImpl.addPatient(patientDto1);
		Assertions.assertNotNull(p);
	}
	@Test
	public void addPatientExceptionTest() throws PatientServiceException
	{
		init();
		Mockito.when(patientRepo.findBySsnId(patientDto1.getSsnId())).thenReturn(Optional.ofNullable(patient));
		Exception e = Assertions.assertThrows(PatientServiceException.class, ()->patientServiceImpl.addPatient(patientDto1));
		Assertions.assertEquals("Patient is already registered!",e.getMessage());
	}
	@Test
	public void getPatientSuccessTest() throws PatientServiceException
	{
		init();
		Mockito.when(patientRepo.findById(1)).thenReturn(Optional.of(patient));
		PatientDto p = patientServiceImpl.getPatientDto(1);
		Assertions.assertNotNull(p);
	}
	@Test
	public void getPatientNotFoundTest() throws PatientServiceException
	{
		init();
		Mockito.when(patientRepo.findById(1)).thenReturn(Optional.ofNullable(null));
		Exception e = Assertions.assertThrows(PatientServiceException.class, ()->patientServiceImpl.getPatientDto(1));
		Assertions.assertEquals("Patient not found!!!", e.getMessage());
	}
	@Test
	public void updatePatientSuccess() throws PatientServiceException
	{
		init();
		patientDto1.setPatientId(1);
		Mockito.when(patientRepo.findById(1)).thenReturn(Optional.of(patient));
		PatientDto p = patientServiceImpl.updatePatient(patientDto1);
		Assertions.assertNotNull(p);
		//Assertions.assertEquals(patientDto1, p);
	}
	@Test
	public void updatePatientNotFoundException() throws PatientServiceException
	{
		init();
		patientDto1.setPatientId(1);
		Mockito.when(patientRepo.findById(1)).thenReturn(Optional.ofNullable(null));
		Exception e = Assertions.assertThrows(PatientServiceException.class, ()->patientServiceImpl.updatePatient(patientDto1));
		Assertions.assertEquals("Patient not found!!!", e.getMessage());
	}
	@Test
	public void deletePatientSuccess() throws PatientServiceException
	{
		init();
		patientDto1.setPatientId(1);
		Mockito.when(patientRepo.findById(1)).thenReturn(Optional.of(patient));
		PatientDto p = patientServiceImpl.deletePatient(patientDto1);
		Assertions.assertNotNull(p);
		//Assertions.assertEquals(patientDto1, p);
	}
	@Test
	public void deletePatientNotFoundException() throws PatientServiceException
	{
		init();
		patientDto1.setPatientId(1);
		Mockito.when(patientRepo.findById(1)).thenReturn(Optional.ofNullable(null));
		Exception e = Assertions.assertThrows(PatientServiceException.class, ()->patientServiceImpl.deletePatient(patientDto1));
		Assertions.assertEquals("Patient not found!!!", e.getMessage());
	}
	@Test
	public void viewPatientsByStatusSuccess() throws PatientServiceException
	{
		init();
		Mockito.when(patientRepo.findByStatus(patient.getStatus())).thenReturn(List.of(patient));
		List<PatientDto> ans = patientServiceImpl.viewPatientsByStatus(patient.getStatus());
		Assertions.assertFalse(ans.isEmpty());
	}
	@Test
	public void viewPatientsByStatusNotFoundException() throws PatientServiceException
	{
		init();
		Mockito.when(patientRepo.findByStatus(patient.getStatus())).thenReturn(new ArrayList());
		Exception e = Assertions.assertThrows(PatientServiceException.class, ()->patientServiceImpl.viewPatientsByStatus(patient.getStatus()));
		Assertions.assertEquals("Pationts not found with status "+patient.getStatus().toString(), e.getMessage());
	}
	@Test
	public void issueMedicineSuccess() throws PatientServiceException
	{
		init();
		Mockito.when(patientRepo.findById(patient.getPatientId())).thenReturn(Optional.of(patient));
		Mockito.when(medicineRepo.findById(medicine.getMedicineId())).thenReturn(Optional.of(medicine));
		
		String ans = patientServiceImpl.issueMedicine(patient.getPatientId(), medicine.getMedicineId(), 1);
		Assertions.assertEquals("Medicines issued successfully", ans);
	}
	@Test
	public void issueMedicinePatientNotFOundException() throws PatientServiceException
	{
		init();
		Mockito.when(patientRepo.findById(patient.getPatientId())).thenReturn(Optional.ofNullable(null));
		Exception e = Assertions.assertThrows(PatientServiceException.class, ()->patientServiceImpl.issueMedicine(patient.getPatientId(), medicine.getMedicineId(), 1));
		Assertions.assertEquals("Patient not found!!!", e.getMessage());
	}
	@Test
	public void issueMedicineMedicineNotFOundException() throws PatientServiceException
	{
		init();
		Mockito.when(patientRepo.findById(patient.getPatientId())).thenReturn(Optional.of(patient));
		Mockito.when(medicineRepo.findById(medicine.getMedicineId())).thenReturn(Optional.ofNullable(null));
		Exception e = Assertions.assertThrows(PatientServiceException.class, ()->patientServiceImpl.issueMedicine(patient.getPatientId(), medicine.getMedicineId(), 1));
		Assertions.assertEquals("Medicine not found", e.getMessage());
	}
	@Test
	public void issueMedicinePatientServiceException() throws PatientServiceException
	{
		init();
		Mockito.when(patientRepo.findById(patient.getPatientId())).thenReturn(Optional.of(patient));
		Mockito.when(medicineRepo.findById(medicine.getMedicineId())).thenReturn(Optional.of(medicine));
		Exception e = Assertions.assertThrows(PatientServiceException.class, ()->patientServiceImpl.issueMedicine(patient.getPatientId(), medicine.getMedicineId(), 100));
		Assertions.assertEquals("Not enough quantity available for required medicine...", e.getMessage());
	}
	
	
	@Test
	public void addDiagnosticsSuccess() throws PatientServiceException, DiagnosticsServiceException
	{
		init();
		Mockito.when(patientRepo.findById(patient.getPatientId())).thenReturn(Optional.of(patient));
		Mockito.when(diagnosisRepo.findById(diagnosis.getTestId())).thenReturn(Optional.of(diagnosis));
		
		String ans = patientServiceImpl.addDiagnostics(patient.getPatientId(), diagnosis.getTestId(), "Positive", "Need iron rich diet");
		Assertions.assertEquals("Diagnostics added successfully", ans);
	}
	@Test
	public void addDiagnosticsPatientNotFoundException() throws PatientServiceException
	{
		init();
		Mockito.when(patientRepo.findById(patient.getPatientId())).thenReturn(Optional.ofNullable(null));
		Exception e = Assertions.assertThrows(PatientServiceException.class, ()->patientServiceImpl.addDiagnostics(patient.getPatientId(), diagnosis.getTestId(), "Positive", "Need iron rich diet"));
		Assertions.assertEquals("Patient not found!!!", e.getMessage());
	}
	@Test
	public void addDiagnosticsDiagnosticsNotFoundException() throws PatientServiceException
	{
		init();
		Mockito.when(patientRepo.findById(patient.getPatientId())).thenReturn(Optional.of(patient));
		Mockito.when(diagnosisRepo.findById(diagnosis.getTestId())).thenReturn(Optional.ofNullable(null));
		Exception e = Assertions.assertThrows(PatientServiceException.class, ()->patientServiceImpl.addDiagnostics(patient.getPatientId(), diagnosis.getTestId(), "Positive", "Need iron rich diet"));
		Assertions.assertEquals("Diagnostics not found", e.getMessage());
	}
}
