package com.project.hms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hms.entity.Medicine;
import com.project.hms.exception.PharmacistServiceException;
import com.project.hms.model.MedicineDto;
import com.project.hms.repository.MedicineRepo;

@Service
@Transactional
public class PharmacistServiceImpl implements PharmacistService{

	@Autowired
	MedicineRepo medicineRepo;
	@Override
	public List<MedicineDto> getMedicine(String medicineName) throws PharmacistServiceException {
		List<Medicine> op = medicineRepo.findByMedicineNameContains(medicineName.toLowerCase());
		if(op.isEmpty())
		{
			throw new PharmacistServiceException("Medicine not found");
		}
		List<MedicineDto> ansList = new ArrayList();
		for(Medicine m: op)
		{
			ansList.add(entityToDto(m));
		}
		return ansList;
	}
	
	public MedicineDto entityToDto(Medicine medicine)
	{
		MedicineDto medicineDto = new MedicineDto(medicine.getMedicineName(),medicine.getQuantity(),medicine.getRate());
		return medicineDto;
	}

}
