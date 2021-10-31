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
import com.project.hms.exception.PatientServiceException;
import com.project.hms.model.AddressDto;
import com.project.hms.model.PatientDto;
import com.project.hms.model.Status;
import com.project.hms.model.TypeOfBed;
import com.project.hms.repository.PatientRepo;
import com.project.hms.service.PatientServiceImpl;

@SpringBootTest
public class PatientServiceImplTest {

	@Mock
	PatientRepo patientRepo;
	@InjectMocks
	PatientServiceImpl patientServiceImpl= new PatientServiceImpl();
	
	AddressDto addressDto;
	PatientDto patientDto1;
	Patient patient;
	Address address;
	
	public void init()
	{
		addressDto = new AddressDto(111,"xyz","abc");
		address = new Address(111,"xyz","abc");
		patientDto1 = new PatientDto(123, "Tony", 70, LocalDate.now(), TypeOfBed.GeneralWard,
				addressDto , "Japan", "Sakura", Status.Active);//already present
		patient = new Patient(123, "Tony", 70, LocalDate.now(), TypeOfBed.GeneralWard,
				address , "Japan", "Sakura", Status.Active);
		
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
}