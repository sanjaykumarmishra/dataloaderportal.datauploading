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
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {
	
    public PatientDTO(String patientName2, String address2, String string, String emailId2, String phoneNumber2,
			String drugId2, String drugName2) {
	}

	private String patientName;
	
	private String address;
	
    @JsonFormat(pattern = "MM/dd/yyyy")
	private Date dateofBirth;
	
	private String emailId;
	
	@Size(min = 10,max = 10,message = "phone number criteria not met")
	private String phoneNumber;
	
	private String drugId;
	
	private String drugName;
	
	

}
