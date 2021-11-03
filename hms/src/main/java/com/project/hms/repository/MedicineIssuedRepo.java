package com.project.hms.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.project.hms.entity.MedicineIssued;

public interface MedicineIssuedRepo extends CrudRepository<MedicineIssued,Integer>{

	List<MedicineIssued> findByPatientId(Integer patientId);
}
