package com.dataloaderportal.datauploading.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.dataloaderportal.datauploading.exception.InvalidTokenException;
import com.dataloaderportal.datauploading.PatientInductionApplication;
import com.dataloaderportal.datauploading.client.AuthClient;
import com.dataloaderportal.datauploading.dto.PatientDTO;
import com.dataloaderportal.datauploading.dto.ValidatingDTO;
import com.dataloaderportal.datauploading.service.PatientService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = PatientInductionApplication.class)
public class InductionControllerTest {
	
	@InjectMocks
	private PatientController patientController;
	
	@Mock
	private AuthClient authclient;
	
	@Mock
	private PatientService patientService;
	
	
	
	@Test
    void submitdetailwithinvalidtoken() {
		String tokenString="AAA";
		ValidatingDTO validatingDTO=new ValidatingDTO(false);
		
		PatientDTO patientDTO=new PatientDTO("Praveen","a-6 coimbatore","Sep 14 05:30:00 IST 2001","praveen525@gmail.com","8220551298","1cxx-cfdvv-dcv","paraceptomel");
		List<PatientDTO> patientList=new ArrayList<>();
		patientList.add(patientDTO);
		
		when(authclient.getValidity(tokenString)).thenReturn(validatingDTO);
		assertThrows(InvalidTokenException.class,()
				->patientController.addpatient(patientList, tokenString));
		

	}
	
	
	
	@Test
	void submitdetails() {
		String tokenString="AAA";
		ValidatingDTO validatingDTO=new ValidatingDTO(true);
		PatientDTO patientDTO=new PatientDTO("Praveen","a-6 coimbatore","Fri Sep 14 05:30:00 IST 2001","praveen525@gmail.com","8220551298","1cxx-cfdvv-dcv","paraceptomel");
		List<PatientDTO> patientList=new ArrayList<>();
		patientList.add(patientDTO);
		
		
		
		when(authclient.getValidity(tokenString)).thenReturn(validatingDTO);
		when(patientService.savePatient(patientList, tokenString)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
		ResponseEntity <List<PatientDTO>> responseEntity=patientController.addpatient(patientList, tokenString);
		assertEquals(HttpStatus.OK,responseEntity.getStatusCode() );
		
	}
	
	
	
	

}
