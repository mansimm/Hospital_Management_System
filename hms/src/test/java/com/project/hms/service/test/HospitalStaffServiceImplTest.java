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
import com.project.hms.utility.HashingUtility;

@SpringBootTest
public class HospitalStaffServiceImplTest {

	@Mock
	HospitalStaffRepo hospitalStaffRepo;
	@InjectMocks
	HospitalStaffServiceImpl hospitalStaffService = new HospitalStaffServiceImpl();
	
	HashingUtility hashingUtility = new HashingUtility();
	
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
	@Test
	public void loginValidTest() throws NoSuchAlgorithmException, HospitalStaffServiceException
	{
		HospitalStaffDto hospitalStaffDto = new HospitalStaffDto("Mickey","Mouse",
				"mm@gmail.com",1234567890L,121212121212L,"Mini","mini123"
				,LocalDateTime.now());
		
		Mockito.when(hospitalStaffRepo.findByUserName(hospitalStaffDto.getUserName())).thenReturn(
				Optional.of(new HospitalStaff("Mickey","Mouse",
						"mm@gmail.com",1234567890L,121212121212L,"Mini",hashingUtility.getHashValue("mini123")
						,LocalDateTime.now())));
		
		String s = hospitalStaffService.login(hospitalStaffDto.getUserName(), hospitalStaffDto.getPassword());
		Assertions.assertEquals("Mini successfully logged in", s);
	}
	
	@Test
	public void loginInvalidExceptionTest() throws HospitalStaffServiceException
	{
		HospitalStaffDto hospitalStaffDto = new HospitalStaffDto("Mickey","Mouse",
				"mm@gmail.com",1234567890L,121212121212L,"Mini","mini123"
				,LocalDateTime.now());
		
		Mockito.when(hospitalStaffRepo.findByUserName(hospitalStaffDto.getUserName())).
		thenReturn(Optional.of(new HospitalStaff("Mickey","Mouse",
				"mm@gmail.com",1234567890L,121212121212L,"Mini","mini123"
				,LocalDateTime.now())));
		Exception e = Assertions.assertThrows(HospitalStaffServiceException.class, 
										()->hospitalStaffService.login(hospitalStaffDto.getUserName(), hospitalStaffDto.getPassword()));
		Assertions.assertEquals("Invalid Login", e.getMessage());
	}
	@Test
	public void loginUserNotFoundExceptionTest() throws HospitalStaffServiceException
	{
		HospitalStaffDto hospitalStaffDto = new HospitalStaffDto("Mickey","Mouse",
				"mm@gmail.com",1234567890L,121212121212L,"Mi","mini123"
				,LocalDateTime.now());
		
		Mockito.when(hospitalStaffRepo.findByUserName(hospitalStaffDto.getUserName())).
		thenReturn(Optional.ofNullable(null));
		Exception e = Assertions.assertThrows(HospitalStaffServiceException.class, 
										()->hospitalStaffService.login(hospitalStaffDto.getUserName(), hospitalStaffDto.getPassword()));
		Assertions.assertEquals("Staff with this username not found", e.getMessage());
	}
}
