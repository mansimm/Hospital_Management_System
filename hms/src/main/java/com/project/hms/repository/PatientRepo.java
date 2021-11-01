package com.project.hms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.project.hms.entity.Address;
import com.project.hms.entity.Patient;
import com.project.hms.model.Status;

public interface PatientRepo extends CrudRepository<Patient,Integer>{

	public Optional<Patient> findBySsnId(Integer ssnId);
	public Optional<Address> findAddressByPatientId(Integer patientId);
	public List<Patient> findByStatus(Status status);
}
