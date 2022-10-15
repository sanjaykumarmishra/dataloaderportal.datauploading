package com.dataloaderportal.datauploading.dto;

import java.util.Date;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Data
@Setter
@NoArgsConstructor
public class PatientDTO {


	private String patientName;
	
	private String address;
	
//    @JsonFormat(pattern = "MM/dd/yyyy")
//	private Date dateofBirth;

	private String dateofBirth;
	
	private String emailId;

	private String phoneNumber;
	
	private String drugId;
	
	private String drugName;

	public PatientDTO(String patientName, String address, String dateofBirth, String emailId, String phoneNumber, String drugId, String drugName) {
		this.patientName = patientName;
		this.address = address;
		this.dateofBirth = dateofBirth;
		this.emailId = emailId;
		this.phoneNumber = phoneNumber;
		this.drugId = drugId;
		this.drugName = drugName;
	}
}
