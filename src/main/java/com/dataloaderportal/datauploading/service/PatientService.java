package com.dataloaderportal.datauploading.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dataloaderportal.datauploading.exception.InvalidDateException;
import com.dataloaderportal.datauploading.repository.FailedDataRepo;
import com.dataloaderportal.datauploading.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dataloaderportal.datauploading.dto.PatientDTO;
import com.dataloaderportal.datauploading.model.InvalidData;
import com.dataloaderportal.datauploading.model.Patient;

import javax.validation.ConstraintViolationException;


@Service
public class PatientService {
	
	
	@Autowired
    PatientRepo patientRepo;
	
	@Autowired
	FailedDataRepo faileddatarepo;
	
	
	
	public ResponseEntity<List<PatientDTO>> savePatient(List<PatientDTO> patientDTOs,String token) throws  NullPointerException{
		
//		SimpleDateFormat formaterDateFormat=new SimpleDateFormat("MM/dd/yyyy");
//
//
//
//
//		for(PatientDTO patientdto: patientDTO) {
//
//			if(patientdto.getPatientName().length()>=5 && patientdto.getPatientName().length()<=30 &&
//					emailValidation(patientdto.getEmailId()) && drugValidation(patientdto.getDrugId()) &&
//					patientdto.getPhoneNumber().length()==10 &&
//					patientdto.getPhoneNumber().matches("[0-9]+")) {
//
//
////			boolean exists=patientRepo.existsById(patientdto.getPatientName());
////			if(exists) {
////				continue;
////			}
//
//			boolean exists=patientRepo.existsByContactNumber(patientdto.getPhoneNumber());
//			if(exists) {
//				continue;
//			}
//
//
//		    Patient patient=new Patient();
//		    patient.setPatientName(patientdto.getPatientName());
//		    patient.setPatientAddress(patientdto.getAddress());
//		    String strdateString=formaterDateFormat.format(patientdto.getDateofBirth());
//	     	patient.setDateofbirth(strdateString);
//	     	System.out.println(strdateString);
//	     	patient.setPatientEmail(patientdto.getEmailId());
//	    	patient.setContactNumber(patientdto.getPhoneNumber());
//	    	patient.setDrugId(patientdto.getDrugId());
//	    	patient.setDrugName(patientdto.getDrugName());
//		    patient.setStatus("inducted");
//		    patientRepo.save(patient);
//		}
//			else {
//
////				boolean exists=faileddatarepo.existsById(patientdto.getPatientName());
////				if(exists) {
////					continue;
////				}
//
//				boolean exists=faileddatarepo.existsByContactNumber(patientdto.getPhoneNumber());
//				if(exists) {
//					continue;
//				}
//
//
//
//			    InvalidData patient=new InvalidData();
//			    patient.setPatientName(patientdto.getPatientName());
//			    patient.setPatientAddress(patientdto.getAddress());
//			    String strdateString=formaterDateFormat.format(patientdto.getDateofBirth());
//		     	patient.setDateofbirth(strdateString);
//		     	System.out.println(strdateString);
//		     	patient.setPatientEmail(patientdto.getEmailId());
//		    	patient.setContactNumber(patientdto.getPhoneNumber());
//		    	patient.setDrugId(patientdto.getDrugId());
//		    	patient.setDrugName(patientdto.getDrugName());
//			    patient.setStatus("failed");
//			    faileddatarepo.save(patient);
//			}
//
//			}
//		SimpleDateFormat formaterDateFormat=new SimpleDateFormat("MM/dd/yyyy");
		for(PatientDTO patientDTO : patientDTOs) {

			try {

				boolean exists=patientRepo.existsByContactNumber(patientDTO.getPhoneNumber());
				if(exists) {
					continue;
				}

//				validateDate(formaterDateFormat.format(patientDTO.getDateofBirth()));
				validateDate(patientDTO.getDateofBirth());


				Patient patient=new Patient();
				patient.setPatientName(patientDTO.getPatientName());
				patient.setPatientAddress(patientDTO.getAddress());
//				String strdateString=formaterDateFormat.format(patientDTO.getDateofBirth());
//				patient.setDateofbirth(strdateString);
				patient.setDateofbirth(patientDTO.getDateofBirth());
				patient.setPatientEmail(patientDTO.getEmailId());
				patient.setContactNumber(patientDTO.getPhoneNumber());
				patient.setDrugId(patientDTO.getDrugId());
				patient.setDrugName(patientDTO.getDrugName());
				patient.setStatus("inducted");
				patientRepo.save(patient);

			} catch (ConstraintViolationException | InvalidDateException | ParseException e) {
//                    System.out.println("ConstraintViolationException for "+patientDTO.getPatientName()+": "+e.getMessage());
//				if(invalidPatientDTORepo.findByPatientContactNumber(patientDTO.getPatientContactNumber())==null) {
//					invalidPatientDTO = new InvalidPatientDTO(patientDTO);
//					invalidPatientDTO.setStatus("FAILED");
//					e.getConstraintViolations().stream().forEach(x -> invalidPatientDTO.setRemarks(x.getMessage()));
//					e.getConstraintViolations().stream().forEach(x -> System.out.println(x.getMessage()));
//					System.out.println(e.getConstraintViolations().size());
//					this.invalidPatientDTORepo.save(invalidPatientDTO);
//				}

				boolean exists=faileddatarepo.existsByContactNumber(patientDTO.getPhoneNumber());
				if(exists) {
					continue;
				}

			    InvalidData patient=new InvalidData();
			    patient.setPatientName(patientDTO.getPatientName());
			    patient.setPatientAddress(patientDTO.getAddress());
//			    String strdateString=formaterDateFormat.format(patientDTO.getDateofBirth());
//		     	patient.setDateofbirth(strdateString);
				patient.setDateofbirth(patientDTO.getDateofBirth());
		     	patient.setPatientEmail(patientDTO.getEmailId());
		    	patient.setContactNumber(patientDTO.getPhoneNumber());
		    	patient.setDrugId(patientDTO.getDrugId());
		    	patient.setDrugName(patientDTO.getDrugName());
			    patient.setStatus("failed");
				if(e instanceof ConstraintViolationException) {
					((ConstraintViolationException) e).getConstraintViolations().stream().forEach(x -> patient.setRemarks(x.getMessage()));
				} else if (e instanceof InvalidDateException) {
					patient.setRemarks(((InvalidDateException) e).getMessage());
				} else if (e instanceof ParseException){
					patient.setRemarks("Invalid Date Pattern: Format -> MM/dd/yyyy");
				}
				faileddatarepo.save(patient);
			}
//
//
//
//
//			} catch (InvalidDateException e) {
////                    System.out.println("Date Constraint Violation for "+patientDTO.getPatientName()+": "+e.getMessage());
//				if(invalidPatientDTORepo.findByPatientContactNumber(patientDTO.getPatientContactNumber())==null) {
//					invalidPatientDTO = new InvalidPatientDTO(patientDTO);
//					invalidPatientDTO.setStatus("FAILED");
//					invalidPatientDTO.setRemarks(e.getMessage());
//					System.out.println(e.getMessage());
//					this.invalidPatientDTORepo.save(invalidPatientDTO);
//				}
//			} catch (ParseException e) {
////                    System.out.println("Date Constraint Violation for "+patientDTO.getPatientName()+": "+" Invalid Date Format");
//				if(invalidPatientDTORepo.findByPatientContactNumber(patientDTO.getPatientContactNumber())==null) {
//					invalidPatientDTO = new InvalidPatientDTO(patientDTO);
//					invalidPatientDTO.setStatus("FAILED");
//					invalidPatientDTO.setRemarks("Invalid Date Pattern: Format -> MM/dd/yyyy");
//					System.out.println("Invalid Date Pattern: Format -> MM/dd/yyyy");
//					this.invalidPatientDTORepo.save(invalidPatientDTO);
//				}
//			}
		}



		
		return new ResponseEntity<>(HttpStatus.OK);
		
	
	}
	
//	public static boolean emailValidation(String email) {
//		String regexString="[A-za-z0-9+_.-]+@(.+)$";
//		Pattern pattern=Pattern.compile(regexString);
//		Matcher matcher=pattern.matcher(email);
//		return matcher.matches();
//	}
//
//	public static boolean drugValidation(String drugid) {
//		String regexString="\\d{5}-\\d{4}-\\d{2}";
//		Pattern pattern=Pattern.compile(regexString);
//		Matcher matcher=pattern.matcher(drugid);
//		return matcher.matches();
//
//	}

	public void validateDate(String dateStr) throws InvalidDateException, ParseException {
		try {
			if(dateStr.length()!=10) throw new InvalidDateException("Invalid Date Pattern: Format -> MM/dd/yyyy");
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			sdf.setLenient(false);
			Date date = sdf.parse(dateStr);
			Date today = sdf.parse(sdf.format(new Date()));
//            System.out.println(today.compareTo(date));
//            System.out.println(today+"-----"+date);
			if(today.compareTo(date) < 0 || today.compareTo(date) == 0) {
				throw new InvalidDateException("DOB should be less than today's date");
			}
		} catch (ParseException e) {
			throw e;
		}


	}



}
