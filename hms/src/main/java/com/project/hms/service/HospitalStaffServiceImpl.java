package com.project.hms.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hms.model.HospitalStaffDto;
import com.project.hms.repository.HospitalStaffRepo;
import com.project.hms.entity.HospitalStaff;
import com.project.hms.exception.HospitalStaffServiceException;

@Transactional
@Service
public class HospitalStaffServiceImpl implements  HospitalStaffService{

	@Autowired
	HospitalStaffRepo hospitalStaffRepo;
	
	@Override
	public String addHopitalStaff(HospitalStaffDto hospitalStaffDto) throws HospitalStaffServiceException {
		
		Optional<HospitalStaff> op = hospitalStaffRepo.findByAddharNumber(hospitalStaffDto.getAddharNumber());
		HospitalStaff staff = op.orElseThrow(()->new HospitalStaffServiceException("Staff already present in system"));
		
		staff = HospitalStaffDtoToEntity(hospitalStaffDto);
		HospitalStaff x = hospitalStaffRepo.save(staff);
		return x.getUserName();
	}
	
	public HospitalStaff HospitalStaffDtoToEntity(HospitalStaffDto hospitalStaffDto)
	{
		HospitalStaff staff = new HospitalStaff(
				hospitalStaffDto.getFname(),
				hospitalStaffDto.getLname(),
				hospitalStaffDto.getEmail(),
				hospitalStaffDto.getContact(),
				hospitalStaffDto.getAddharNumber(),
				hospitalStaffDto.getUserName(),
				hospitalStaffDto.getPassword(),
				hospitalStaffDto.getTimestamp()
				);
		return staff;
		
	}

}
