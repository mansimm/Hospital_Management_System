package com.project.hms.api;

import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hms.exception.HospitalStaffServiceException;
import com.project.hms.model.HospitalStaffDto;
import com.project.hms.service.HospitalStaffService;

@CrossOrigin
@RestController
@RequestMapping(value="HospitalStaffAPI")
public class HospitalStaffApi {

	@Autowired
	HospitalStaffService hospitalStaffService;
	
	@PostMapping(value="add/hospitalStaff")
	public ResponseEntity<String> addHopitalStaff(@Valid @RequestBody HospitalStaffDto hospitalStaffDto) throws HospitalStaffServiceException, NoSuchAlgorithmException
	{
		String s = hospitalStaffService.addHopitalStaff(hospitalStaffDto);
		return new ResponseEntity<String>(s,HttpStatus.OK);
	}
	
	@GetMapping(value="login/hospitalStaff")
	public ResponseEntity<String> login(@RequestBody @Valid HospitalStaffDto hospitalStaffDto) throws NoSuchAlgorithmException, HospitalStaffServiceException
	{
		String s = hospitalStaffService.login(hospitalStaffDto.getUserName(), hospitalStaffDto.getPassword());
		return new ResponseEntity<String>(s,HttpStatus.OK);
	}
}
