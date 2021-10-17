package com.project.hms.service.test;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.hms.entity.HospitalStaff;
import com.project.hms.exception.HospitalStaffServiceException;
import com.project.hms.model.HospitalStaffDto;
import com.project.hms.repository.HospitalStaffRepo;
import com.project.hms.service.HospitalStaffServiceImpl;

@SpringBootTest
public class HospitalStaffServiceImplTest {

	@Mock
	HospitalStaffRepo hospitalStaffRepo;
	@InjectMocks
	HospitalStaffServiceImpl hospitalStaffService = new HospitalStaffServiceImpl();
	
	@Test
	public void addHopitalStaffSuccessTest() throws HospitalStaffServiceException, NoSuchAlgorithmException
	{
		HospitalStaffDto hospitalStaffDto = new HospitalStaffDto("Mickey","Mouse",
				"mm@gmail.com",1234567890L,121212121212L,"Mini","mini123"
				,LocalDateTime.now());
		
		HospitalStaff staff = new HospitalStaff("Mickey","Mouse",
				"mm@gmail.com",1234567890L,121212121212L,"Mini","mini123"
				,LocalDateTime.now());
		
		Mockito.when(hospitalStaffRepo.findByAddharNumber(hospitalStaffDto.getAddharNumber())).
		thenReturn(Optional.ofNullable(null));
		//Mockito.when(hospitalStaffRepo.save(staff)).thenReturn(staff);
		
		String uname = hospitalStaffService.addHopitalStaff(hospitalStaffDto);
		Assertions.assertEquals(hospitalStaffDto.getUserName(), uname);
	}
	@Test
	public void addHopitalStaffExceptionTest() throws HospitalStaffServiceException
	{
		HospitalStaffDto hospitalStaffDto = new HospitalStaffDto("Mickey","Mouse",
				"mm@gmail.com",1234567890L,121212121212L,"Mini","mini123"
				,LocalDateTime.now());
		
		Mockito.when(hospitalStaffRepo.findByAddharNumber(hospitalStaffDto.getAddharNumber())).
		thenReturn(Optional.of(new HospitalStaff("Mickey","Mouse",
				"mm@gmail.com",1234567890L,121212121212L,"Mini","mini123"
				,LocalDateTime.now())));
		Exception e = Assertions.assertThrows(HospitalStaffServiceException.class, 
										()->hospitalStaffService.addHopitalStaff(hospitalStaffDto));
		Assertions.assertEquals("Staff already present in system", e.getMessage());
	}
}
