package com.project.hms.service;

import java.util.List;

import com.project.hms.entity.Diagnosis;
import com.project.hms.exception.DiagnosticsServiceException;
import com.project.hms.model.DiagnosisDto;

public interface DiagnosticsService {

	List<DiagnosisDto> findByTestNameContains(String testName) throws DiagnosticsServiceException;
}
