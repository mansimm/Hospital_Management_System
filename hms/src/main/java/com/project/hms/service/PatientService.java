package com.project.hms.service;


import com.project.hms.entity.Patient;
import com.project.hms.exception.PatientServiceException;
import com.project.hms.model.PatientDto;

public interface PatientService {

	public Patient addPatient(PatientDto patient) throws PatientServiceException;
	public Patient getPatient(Integer patientId) throws PatientServiceException;
	public PatientDto getPatientDto(Integer patientId) throws PatientServiceException;
	public PatientDto updatePatient(PatientDto patient) throws PatientServiceException;
}
