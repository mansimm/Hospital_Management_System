package com.project.hms.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hms.exception.DiagnosticsServiceException;
import com.project.hms.model.DiagnosisDto;
import com.project.hms.service.DiagnosticsServiceImpl;

@CrossOrigin
@RestController
@RequestMapping(value="DiagnosisAPI")
public class DiagnosisApi {

	@Autowired
	DiagnosticsServiceImpl diagnosticsServiceImpl;
	
	@GetMapping(value="diagnosis/{testName}")
	public ResponseEntity<List<DiagnosisDto>> getDiagnosis(@PathVariable String testName) throws DiagnosticsServiceException   
	{
		List<DiagnosisDto> ans = diagnosticsServiceImpl.findByTestNameContains(testName);
		return new ResponseEntity(ans,HttpStatus.OK);
	}
}
