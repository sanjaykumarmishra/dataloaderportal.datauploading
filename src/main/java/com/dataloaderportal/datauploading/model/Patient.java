package com.dataloaderportal.datauploading.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "patients")
public class Patient {
	
	
	

	@Id
	@Column(name="patient_Name",unique=true)
	private String patientName;
	
	@Column(name="patient_Address")
	private String patientAddress;
	
	@Column(name = "Date_of_Birth")
	private String dateofbirth;
	
	@Column(name = "patient_Email")
	private String patientEmail;
	
	@Column(name="contact_Number")
	private String contactNumber;
	
	@Column(name = "drug_Id")
	private String drugId;
	
	@Column(name = "drug_Name")
	private String drugName;
	
	
	
	@Column(name = "status")
	private String status;
	
	
	
	
	

}
