package com.project.hms.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hms.entity.Diagnosis;
import com.project.hms.entity.Medicine;
import com.project.hms.exception.DiagnosticsServiceException;
import com.project.hms.exception.PharmacistServiceException;
import com.project.hms.model.DiagnosisDto;
import com.project.hms.model.MedicineDto;
import com.project.hms.repository.DiagnosisRepo;

@Service
@Transactional
public class DiagnosticsServiceImpl implements DiagnosticsService{

	@Autowired
	DiagnosisRepo diagnosisRepo;
	@Override
	public List<DiagnosisDto> findByTestNameContains(String testName) throws DiagnosticsServiceException {
		List<Diagnosis> op = diagnosisRepo.findByTestNameContains(testName.toLowerCase());
		if(op.isEmpty())
		{
			throw new DiagnosticsServiceException("Diagnosis not found");
		}
		List<DiagnosisDto> ansList = new ArrayList();
		for(Diagnosis d: op)
		{
			ansList.add(entityToDto(d));
		}
		return ansList;
	}
	private DiagnosisDto entityToDto(Diagnosis d) {
		DiagnosisDto diagnosisDto = new DiagnosisDto(d.getTestName(),d.getTestDescription(),d.getTestCharges());
		return diagnosisDto;
	}

}
