package com.project.hms.service.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.hms.entity.Medicine;
import com.project.hms.exception.PatientServiceException;
import com.project.hms.exception.PharmacistServiceException;
import com.project.hms.model.MedicineDto;
import com.project.hms.repository.MedicineRepo;
import com.project.hms.service.PharmacistServiceImpl;

@SpringBootTest
public class PharmacistServiceImplTest {

	@Mock
	MedicineRepo medicineRepo;
	@InjectMocks
	PharmacistServiceImpl pharmacistServiceImpl = new PharmacistServiceImpl();
	
	Medicine medicine;
	public void init()
	{
		medicine = new Medicine("Paracetemol",10,20.0);
	}
	@Test
	public void getMedicineSuccessTest() throws PharmacistServiceException
	{
		init();
		Mockito.when(medicineRepo.findByMedicineNameContains(medicine.getMedicineName().toLowerCase())).thenReturn(List.of(medicine));
		List<MedicineDto> ans = pharmacistServiceImpl.getMedicine(medicine.getMedicineName());
		Assertions.assertFalse(ans.isEmpty());
	}
	@Test
	public void getMedicineExceptionTest() throws PatientServiceException
	{
		init();
		Mockito.when(medicineRepo.findByMedicineNameContains(medicine.getMedicineName().toLowerCase())).thenReturn(new ArrayList());
		Exception e = Assertions.assertThrows(PharmacistServiceException.class, ()->pharmacistServiceImpl.getMedicine(medicine.getMedicineName()));
		Assertions.assertEquals("Medicine not found", e.getMessage());
	}
}
