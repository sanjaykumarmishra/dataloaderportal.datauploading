package com.dataloaderportal.datauploading.service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dataloaderportal.datauploading.repository.FailedDataRepo;
import com.dataloaderportal.datauploading.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dataloaderportal.datauploading.dto.PatientDTO;
import com.dataloaderportal.datauploading.model.InvalidData;
import com.dataloaderportal.datauploading.model.Patient;


@Service
public class PatientService {
	
	
	@Autowired
    PatientRepo patientRepo;
	
	@Autowired
	FailedDataRepo faileddatarepo;
	
	
	
	public ResponseEntity<List<PatientDTO>> savePatient(List<PatientDTO> patientDTO,String token) throws  NullPointerException{
		
		SimpleDateFormat formaterDateFormat=new SimpleDateFormat("MM/dd/yyyy");
		

		

		for(PatientDTO patientdto: patientDTO) {
			
			if(patientdto.getPatientName().length()>=5 && patientdto.getPatientName().length()<=30 && 
					emailValidation(patientdto.getEmailId()) && drugValidation(patientdto.getDrugId()) &&
					patientdto.getPhoneNumber().length()==10 &&
					patientdto.getPhoneNumber().matches("[0-9]+")) {
			
			
//			boolean exists=patientRepo.existsById(patientdto.getPatientName());
//			if(exists) {
//				continue;
//			}

			boolean exists=patientRepo.existsByContactNumber(patientdto.getPhoneNumber());
			if(exists) {
				continue;
			}


		    Patient patient=new Patient();
		    patient.setPatientName(patientdto.getPatientName());
		    patient.setPatientAddress(patientdto.getAddress());
		    String strdateString=formaterDateFormat.format(patientdto.getDateofBirth());
	     	patient.setDateofbirth(strdateString);
	     	System.out.println(strdateString);
	     	patient.setPatientEmail(patientdto.getEmailId());
	    	patient.setContactNumber(patientdto.getPhoneNumber());
	    	patient.setDrugId(patientdto.getDrugId());
	    	patient.setDrugName(patientdto.getDrugName());
		    patient.setStatus("inducted");
		    patientRepo.save(patient);
		}
			else {

//				boolean exists=faileddatarepo.existsById(patientdto.getPatientName());
//				if(exists) {
//					continue;
//				}

				boolean exists=faileddatarepo.existsByContactNumber(patientdto.getPhoneNumber());
				if(exists) {
					continue;
				}



			    InvalidData patient=new InvalidData();
			    patient.setPatientName(patientdto.getPatientName());
			    patient.setPatientAddress(patientdto.getAddress());
			    String strdateString=formaterDateFormat.format(patientdto.getDateofBirth());
		     	patient.setDateofbirth(strdateString);
		     	System.out.println(strdateString);
		     	patient.setPatientEmail(patientdto.getEmailId());
		    	patient.setContactNumber(patientdto.getPhoneNumber());
		    	patient.setDrugId(patientdto.getDrugId());
		    	patient.setDrugName(patientdto.getDrugName());
			    patient.setStatus("failed");
			    faileddatarepo.save(patient);
			}
				
			}
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	
	}
	
	public static boolean emailValidation(String email) {
		String regexString="[A-za-z0-9+_.-]+@(.+)$";
		Pattern pattern=Pattern.compile(regexString);
		Matcher matcher=pattern.matcher(email);
		return matcher.matches();
	}
	
	public static boolean drugValidation(String drugid) {
		String regexString="\\d{5}-\\d{4}-\\d{2}";
		Pattern pattern=Pattern.compile(regexString);
		Matcher matcher=pattern.matcher(drugid);
		return matcher.matches();
		
	}
}
