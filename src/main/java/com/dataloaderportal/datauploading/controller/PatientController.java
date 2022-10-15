package com.dataloaderportal.datauploading.controller;


import java.util.List;

import com.dataloaderportal.datauploading.exception.InvalidTokenException;
import com.dataloaderportal.datauploading.client.AuthClient;
import com.dataloaderportal.datauploading.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.dataloaderportal.datauploading.dto.PatientDTO;


@RestController
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private AuthClient authClient;
	
	@CrossOrigin(origins= {"*"})
	@PostMapping(path = "/add-patient")
	public ResponseEntity<List<PatientDTO>> addpatient(@RequestBody List<PatientDTO> patientDTO,@RequestHeader(name = "Authorization",required=true) String token)
	throws InvalidTokenException,NullPointerException{
		
		System.out.println("Inside addPatient of Patient Controller");
		
		if(!authClient.getValidity(token).isValidStatus()) {
			throw new InvalidTokenException("Token is either invalid");
		}
		System.out.println(patientDTO);
	
		return patientService.savePatient(patientDTO, token);
	}
	
	
	

}
