package com.project.hms.service.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.hms.entity.Diagnosis;
import com.project.hms.exception.DiagnosticsServiceException;
import com.project.hms.exception.PatientServiceException;
import com.project.hms.exception.PharmacistServiceException;
import com.project.hms.model.DiagnosisDto;
import com.project.hms.repository.DiagnosisRepo;
import com.project.hms.service.DiagnosticsServiceImpl;

@SpringBootTest
public class DiagnosticsServiceImplTest {

	@Mock
	DiagnosisRepo diagnosisRepo;
	@InjectMocks
	DiagnosticsServiceImpl diagnosticsServiceImpl = new DiagnosticsServiceImpl();
	
	Diagnosis diagnosis;
	public void init()
	{
		diagnosis = new Diagnosis("Hemogram","Hemogram comprises of CBC and ESR",150.0);
	}
	@Test
	public void findByTestNameContainsSuccess() throws DiagnosticsServiceException
	{
		init();
		Mockito.when(diagnosisRepo.findByTestNameContains(diagnosis.getTestName().toLowerCase())).thenReturn(List.of(diagnosis));
		List<DiagnosisDto> ans = diagnosticsServiceImpl.findByTestNameContains(diagnosis.getTestName());
		Assertions.assertFalse(ans.isEmpty());
	}
	
	@Test
	public void findByTestNameContainsExceptionTest() throws PatientServiceException
	{
		init();
		Mockito.when(diagnosisRepo.findByTestNameContains(diagnosis.getTestName().toLowerCase())).thenReturn(new ArrayList());
		Exception e = Assertions.assertThrows(DiagnosticsServiceException.class, ()->diagnosticsServiceImpl.findByTestNameContains(diagnosis.getTestName()));
		Assertions.assertEquals("Diagnosis not found", e.getMessage());
	}
	
}
