package com.project.hms.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.project.hms.entity.DiagnosticsConducted;

public interface DiagnosticsConductedRepo extends CrudRepository<DiagnosticsConducted,Integer>{

	List<DiagnosticsConducted> findByPatientId(Integer patientId);
}
