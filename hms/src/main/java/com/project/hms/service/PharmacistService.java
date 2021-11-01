package com.project.hms.service;

import java.util.List;
import java.util.Optional;

import com.project.hms.entity.Medicine;
import com.project.hms.exception.PharmacistServiceException;
import com.project.hms.model.MedicineDto;

public interface PharmacistService {

	List<MedicineDto> getMedicine(String medicineName) throws PharmacistServiceException;
}
