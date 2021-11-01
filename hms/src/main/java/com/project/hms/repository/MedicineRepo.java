package com.project.hms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.project.hms.entity.Medicine;

public interface MedicineRepo extends CrudRepository<Medicine,Integer>{

	List<Medicine> findByMedicineNameContains(String medicineName);
}
