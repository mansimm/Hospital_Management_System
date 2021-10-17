package com.project.hms.service;

import java.security.NoSuchAlgorithmException;

import com.project.hms.exception.HospitalStaffServiceException;
import com.project.hms.model.HospitalStaffDto;

public interface HospitalStaffService {

	public String addHopitalStaff(HospitalStaffDto hospitalStaffDto) throws HospitalStaffServiceException, NoSuchAlgorithmException;
	public String login(String uname, String pass) throws HospitalStaffServiceException, NoSuchAlgorithmException;
}
