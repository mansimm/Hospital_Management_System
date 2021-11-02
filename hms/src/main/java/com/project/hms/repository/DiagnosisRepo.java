package com.project.hms.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.hms.entity.Diagnosis;

public interface DiagnosisRepo extends CrudRepository<Diagnosis,Integer> {

}
