package com.project.hms.api;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hms.exception.HospitalStaffServiceException;
import com.project.hms.exception.PharmacistServiceException;
import com.project.hms.model.HospitalStaffDto;
import com.project.hms.model.MedicineDto;
import com.project.hms.service.PharmacistServiceImpl;

@CrossOrigin
@RestController
@RequestMapping(value="MedicineAPI")
public class MedicineApi {

	@Autowired
	PharmacistServiceImpl pharmacistServiceImpl;
	
	@GetMapping(value="medicine/{medicineName}")
	public ResponseEntity<List<MedicineDto>> getMedicine(@PathVariable String medicineName) throws PharmacistServiceException 
	{
		List<MedicineDto> ans = pharmacistServiceImpl.getMedicine(medicineName);
		return new ResponseEntity(ans,HttpStatus.OK);
	}
}
