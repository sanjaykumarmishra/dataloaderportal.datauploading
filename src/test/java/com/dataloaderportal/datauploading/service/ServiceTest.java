package com.dataloaderportal.datauploading.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.dataloaderportal.datauploading.controller.PatientController;
import com.dataloaderportal.datauploading.dto.PatientDTO;
import com.dataloaderportal.datauploading.repository.PatientRepo;

@SpringBootTest(classes = PatientService.class)
public class ServiceTest {
	
	
	@MockBean
	private PatientRepo patientRepo;
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private PatientController PatientController;
	
	
	@Test
	void submittest() {
		String tokenString="AAA";
		
		PatientDTO patientDTO=new PatientDTO("Praveen","a-6 coimbatore","Sep 14 05:30:00 IST 2001","praveen525@gmail.com","8220551298","1cxx-cfdvv-dcv","paraceptomel");
		List<PatientDTO> patientList=new ArrayList<>();
		patientList.add(patientDTO);
		
		
		
		
		
		
		
	}
	

}
