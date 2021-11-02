package com.project.hms.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hms.entity.Address;
import com.project.hms.entity.Diagnosis;
import com.project.hms.entity.DiagnosticsConducted;
import com.project.hms.entity.Medicine;
import com.project.hms.entity.MedicineIssued;
import com.project.hms.entity.Patient;
import com.project.hms.exception.DiagnosticsServiceException;
import com.project.hms.exception.PatientServiceException;
import com.project.hms.exception.PharmacistServiceException;
import com.project.hms.model.AddressDto;
import com.project.hms.model.PatientDto;
import com.project.hms.model.Status;
import com.project.hms.model.TypeOfBed;
import com.project.hms.repository.DiagnosisRepo;
import com.project.hms.repository.MedicineIssuedRepo;
import com.project.hms.repository.MedicineRepo;
import com.project.hms.repository.PatientRepo;
import com.project.hms.repository.DiagnosticsConductedRepo;

@Service
@Transactional
public class PatientServiceImpl implements PatientService{

	@Autowired
	PatientRepo patientRepo;
	@Autowired
	MedicineRepo medicineRepo;
	@Autowired
	MedicineIssuedRepo medicineIssuedRepo;
	@Autowired
	DiagnosisRepo diagnosisRepo;
	@Autowired
	DiagnosticsConductedRepo diagnosticsConductedRepo;
	
	@Override
	public Patient addPatient(PatientDto patient) throws PatientServiceException {
		
		Optional<Patient> op = patientRepo.findBySsnId(patient.getSsnId());
		if(op.isPresent())
		{
			throw new PatientServiceException("Patient is already registered!");
		}
		
		Address a = new Address(patient.getAddress().getHouseNo(),
				patient.getAddress().getArea(),patient.getAddress().getStreet());
		
		Patient p = new Patient(patient.getSsnId(),patient.getName(),patient.getAge(),patient.getDateOfAdmission()
				,patient.getTypeOfBed(),a,patient.getCountry(),patient.getCity(),patient.getStatus());
		
		patientRepo.save(p);
		return p;
	}

	@Override
	public Patient getPatient(Integer patientId) throws PatientServiceException {
		Optional<Patient> op = patientRepo.findById(patientId);
		Patient p = op.orElseThrow(()-> new PatientServiceException("Patient not found!!!"));
		return p;
	}

	@Override
	public PatientDto updatePatient(PatientDto patient) throws PatientServiceException {
		Patient p = getPatient(patient.getPatientId());
		Address a = p.getAddress();
		if(a==null)
			{
				throw new PatientServiceException("Patient address not found...");
			}
		a.setArea(patient.getAddress().getArea());
		a.setHouseNo(patient.getAddress().getHouseNo());
		a.setStreet(patient.getAddress().getStreet());
		
		p.setAddress(a);
		p.setAge(patient.getAge());
		p.setCity(patient.getCity());
		p.setCountry(patient.getCountry());
		p.setDateOfAdmission(patient.getDateOfAdmission());
		p.setName(patient.getName());
		p.setSsnId(patient.getSsnId());
		p.setStatus(patient.getStatus());
		p.setTypeOfBed(patient.getTypeOfBed());
		
		return entityToDto(p);
	}

	@Override
	public PatientDto getPatientDto(Integer patientId) throws PatientServiceException {
		Optional<Patient> op = patientRepo.findById(patientId);
		Patient p = op.orElseThrow(()-> new PatientServiceException("Patient not found!!!"));
		return entityToDto(p);
	}
	
	public PatientDto entityToDto(Patient p)
	{
		Address a = p.getAddress();
		AddressDto address = new AddressDto(a.getHouseNo(),a.getArea(),a.getStreet());
		PatientDto patient = new PatientDto(p.getSsnId(),p.getName(),p.getAge(),p.getDateOfAdmission(),
				p.getTypeOfBed(),address,p.getCountry(),p.getCity(),p.getStatus());
		return patient;
		
	}

	@Override
	public PatientDto deletePatient(PatientDto patient) throws PatientServiceException {
		Optional<Patient> op = patientRepo.findById(patient.getPatientId());
		Patient p = op.orElseThrow(()-> new PatientServiceException("Patient not found!!!"));
		patientRepo.delete(p);
		return entityToDto(p);
	}

	@Override
	public List<PatientDto> viewPatientsByStatus(Status status) throws PatientServiceException {
		List<Patient> patientsList = patientRepo.findByStatus(status);
		if(patientsList.isEmpty())
		{
			throw new PatientServiceException("Pationts not found with status "+status.toString());
		}
		List<PatientDto> ansList = new ArrayList();
		for(Patient p:patientsList)
		{
			ansList.add(entityToDto(p));
		}
		return ansList;
	}

	@Override
	public String issueMedicine(Integer patientId, Integer medicineId,Integer quantity) throws PatientServiceException {
		Optional<Patient> op = patientRepo.findById(patientId);
		Patient p = op.orElseThrow(()-> new PatientServiceException("Patient not found!!!"));
		Optional<Medicine> opMed = medicineRepo.findById(medicineId);
		Medicine m = opMed.orElseThrow(()-> new PatientServiceException("Medicine not found"));
		
		if(quantity>m.getQuantity())
		{
			throw new PatientServiceException("Not enough quantity available for required medicine..");
		}
		//decrease medicine quantity
		m.setQuantity(m.getQuantity()-quantity);

		MedicineIssued medicineIssued = new MedicineIssued(p.getPatientId(),m.getMedicineId(),quantity);
		medicineIssuedRepo.save(medicineIssued);
		return "Medicines issued successfully";
	}

	@Override
	public String addDiagnostics(Integer patientId, Integer testId, String result, String comment) throws PatientServiceException, DiagnosticsServiceException {
		Optional<Patient> op = patientRepo.findById(patientId);
		Patient p = op.orElseThrow(()-> new PatientServiceException("Patient not found!!!"));
		Optional<Diagnosis> opDia = diagnosisRepo.findById(testId);
		Diagnosis d = opDia.orElseThrow(()-> new DiagnosticsServiceException("Diagnostics not found"));
		
		DiagnosticsConducted diagnosticsConducted = new DiagnosticsConducted(p.getPatientId(),d.getTestId(),
				result,comment);
		diagnosticsConductedRepo.save(diagnosticsConducted);
		return "Diagnostics added successfully";
	}

}
