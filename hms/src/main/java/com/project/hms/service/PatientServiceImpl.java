package com.project.hms.service;

import java.time.LocalDate;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hms.entity.Address;
import com.project.hms.entity.Patient;
import com.project.hms.exception.PatientServiceException;
import com.project.hms.model.PatientDto;
import com.project.hms.model.Status;
import com.project.hms.model.TypeOfBed;
import com.project.hms.repository.PatientRepo;

@Service
@Transactional
public class PatientServiceImpl implements PatientService{

	@Autowired
	PatientRepo patientRepo;
	
	@Override
	public Patient addPatient(PatientDto patient) throws PatientServiceException {
		
		Optional<Patient> op = patientRepo.findBySsnId(patient.getSsnId());
		if(op.isPresent())
		{
			throw new PatientServiceException("Patient is already registered");
		}
		
		Address a = new Address(patient.getAddress().getAddressId(),patient.getAddress().getHouseNo(),
				patient.getAddress().getArea(),patient.getAddress().getStreet());
		
		Patient p = new Patient(patient.getSsnId(),patient.getName(),patient.getAge(),patient.getDateOfAdmission()
				,patient.getTypeOfBed(),a,patient.getCountry(),patient.getCity(),patient.getStatus());
		
		patientRepo.save(p);
		return p;
	}

}
