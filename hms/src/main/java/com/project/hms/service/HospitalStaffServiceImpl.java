package com.project.hms.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.hms.model.HospitalStaffDto;
import com.project.hms.repository.HospitalStaffRepo;
import com.project.hms.entity.HospitalStaff;
import com.project.hms.exception.HospitalStaffServiceException;

public class HospitalStaffServiceImpl implements  HospitalStaffService{

	@Autowired
	HospitalStaffRepo hospitalStaffRepo;
	
	@Override
	public Integer addHopitalStaff(HospitalStaffDto hospitalStaffDto) throws HospitalStaffServiceException {
		
		Optional<HospitalStaff> op = hospitalStaffRepo.findByAddharNumber(hospitalStaffDto.getAddharNumber());
		HospitalStaff staff = op.orElseThrow(()->new HospitalStaffServiceException(""));
		return null;
	}

}
