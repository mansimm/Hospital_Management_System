package com.project.hms.service;

import com.project.hms.exception.HospitalStaffServiceException;
import com.project.hms.model.HospitalStaffDto;

public interface HospitalStaffService {

	public String addHopitalStaff(HospitalStaffDto hospitalStaffDto) throws HospitalStaffServiceException;
}
