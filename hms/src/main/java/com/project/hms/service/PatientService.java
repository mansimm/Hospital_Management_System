package com.project.hms.service;


import java.util.List;

import com.project.hms.entity.Patient;
import com.project.hms.exception.PatientServiceException;
import com.project.hms.model.PatientDto;
import com.project.hms.model.Status;

public interface PatientService {

	public Patient addPatient(PatientDto patient) throws PatientServiceException;
	public Patient getPatient(Integer patientId) throws PatientServiceException;
	public PatientDto getPatientDto(Integer patientId) throws PatientServiceException;
	public PatientDto updatePatient(PatientDto patient) throws PatientServiceException;
	public PatientDto deletePatient(PatientDto patient) throws PatientServiceException;
	public List<PatientDto> viewPatientsByStatus(Status status) throws PatientServiceException;
	public String issueMedicine(Integer patientId,Integer medicineId,Integer quantity) throws PatientServiceException;

}
