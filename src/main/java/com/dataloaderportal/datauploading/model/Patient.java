package com.dataloaderportal.datauploading.model;


import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "patients")
public class Patient {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer patientId;


	@Column(name="patient_Name",unique=true)
	private String patientName;
	
	@Column(name="patient_Address")
	private String patientAddress;
	
	@Column(name = "Date_of_Birth")
	private String dateofbirth;
	
	@Column(name = "patient_Email")
	private String patientEmail;
	
	@Column(name="contact_Number", unique=true)
	private String contactNumber;
	
	@Column(name = "drug_Id")
	private String drugId;
	
	@Column(name = "drug_Name")
	private String drugName;
	
	
	
	@Column(name = "status")
	private String status;


	public Patient(String patientName, String patientAddress, String dateofbirth, String patientEmail, String contactNumber, String drugId, String drugName, String status) {
		this.patientName = patientName;
		this.patientAddress = patientAddress;
		this.dateofbirth = dateofbirth;
		this.patientEmail = patientEmail;
		this.contactNumber = contactNumber;
		this.drugId = drugId;
		this.drugName = drugName;
		this.status = status;
	}
}
