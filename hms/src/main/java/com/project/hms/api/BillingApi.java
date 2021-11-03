package com.project.hms.api;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hms.exception.BillingServiceException;
import com.project.hms.exception.DiagnosticsServiceException;
import com.project.hms.model.BillDto;
import com.project.hms.model.DiagnosisDto;
import com.project.hms.service.BillingServiceImpl;


@CrossOrigin
@RestController
@RequestMapping(value="BillingAPI")
public class BillingApi {

	@Autowired
	BillingServiceImpl billingServiceImpl;
	
	@GetMapping(value="bill/{patientId}")
	public ResponseEntity<BillDto> calculateTotalBill(@PathVariable 
			@NotNull(message = "{PatientValidator.patientid.null}")
			Integer patientId) throws BillingServiceException   
	{
		BillDto bill = billingServiceImpl.calculateTotalBill(patientId);
		return new ResponseEntity(bill,HttpStatus.OK);
	}
	@GetMapping(value="bill/{patientId}/{dateOfDischarge}")
	public ResponseEntity<String> setDateOfDischarge(@PathVariable 
			@NotNull(message = "{PatientValidator.patientid.null}")
			Integer patientId,
			@PathVariable 
			@NotNull(message = "{PatientValidator.dateofdischarge.null}")
			LocalDate dateOfDischarge) throws BillingServiceException
	{
		String s = billingServiceImpl.setDateOfDischarge(patientId, dateOfDischarge);
		return new ResponseEntity(s,HttpStatus.OK);
		
	}
}
