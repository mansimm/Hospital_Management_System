package com.project.hms.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hms.entity.Patient;
import com.project.hms.exception.PatientServiceException;
import com.project.hms.model.PatientDto;
import com.project.hms.service.PatientService;

@CrossOrigin
@RestController
@RequestMapping(value="/PatientAPI")
@Valid
public class PatientApi {

	@Autowired
	PatientService	patientService;
	
	@PostMapping(value="/add/patient")
	public ResponseEntity<String> addPatient(@RequestBody @Valid PatientDto patient) throws PatientServiceException
	{
		Patient p = patientService.addPatient(patient);
		return new ResponseEntity(p.getName()+" is registerd successfully !",HttpStatus.OK);
	}
}
