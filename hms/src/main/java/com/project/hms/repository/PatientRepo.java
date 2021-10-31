package com.project.hms.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.project.hms.entity.Patient;

public interface PatientRepo extends CrudRepository<Patient,Integer>{

	public Optional<Patient> findBySsnId(Integer ssnId);
}
