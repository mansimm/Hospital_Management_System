package com.project.hms.api;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hms.entity.Patient;
import com.project.hms.exception.PatientServiceException;
import com.project.hms.model.PatientDto;
import com.project.hms.model.Status;
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
	@GetMapping(value="/get/patient/{patientId}")
	public ResponseEntity<PatientDto> getPatient(@PathVariable 
			@NotNull(message="{PatientValidator.patientid.null}")
			Integer patientId) throws PatientServiceException
	{
		PatientDto p = patientService.getPatientDto(patientId);
		return new ResponseEntity(p,HttpStatus.OK);
	}
	@PutMapping(value="/update/patient")
	public ResponseEntity<PatientDto> updatePatient(@RequestBody @Valid PatientDto patient) throws PatientServiceException
	{
		PatientDto p = patientService.updatePatient(patient);
		return new ResponseEntity(p,HttpStatus.OK);	
	}
	@DeleteMapping(value="/delete/patient")
	public ResponseEntity<PatientDto> deletePatient(@RequestBody @Valid PatientDto patient) throws PatientServiceException
	{
		PatientDto p = patientService.deletePatient(patient);
		return new ResponseEntity(p,HttpStatus.OK);	
	}
	@GetMapping(value="/find/patient/{status}")
	public ResponseEntity<List<PatientDto>> findPatientByStatus(@PathVariable
																@NotNull(message= "{PatientValidator.status.null}")
																Status status) throws PatientServiceException
	{
		List<PatientDto> ansList = patientService.viewPatientsByStatus(status);
		return new ResponseEntity(ansList,HttpStatus.OK);
	}
	@PostMapping(value="issue/medicine/{patientId}/{medicineId}/{quantity}")
	public ResponseEntity<String> issueMedicine(@PathVariable Integer patientId,
			@PathVariable Integer medicineId,
			@PathVariable Integer quantity) throws PatientServiceException
	{
		String s = patientService.issueMedicine(patientId, medicineId, quantity);
		return new ResponseEntity(s,HttpStatus.CREATED);
		
	}
}
