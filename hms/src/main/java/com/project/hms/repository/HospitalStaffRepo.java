package com.project.hms.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.project.hms.entity.HospitalStaff;

public interface HospitalStaffRepo extends CrudRepository<HospitalStaff,Integer>{

	Optional<HospitalStaff> findByAddharNumber(Long addharNumber);
	Optional<HospitalStaff> findByUserName(String userName);

}
