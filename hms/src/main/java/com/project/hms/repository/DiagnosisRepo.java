package com.project.hms.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.project.hms.entity.Diagnosis;
import com.project.hms.model.DiagnosisDto;


public interface DiagnosisRepo extends CrudRepository<Diagnosis,Integer> {

	List<Diagnosis> findByTestNameContains(String testName);
	Diagnosis findByTestId(Integer testId);
}
