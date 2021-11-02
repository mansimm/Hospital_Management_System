package com.project.hms.service;

import java.util.List;

import com.project.hms.entity.Diagnosis;

public interface DiagnosticsService {

	List<Diagnosis> findByTestNameContains(String testName);
}
