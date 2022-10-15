package com.dataloaderportal.datauploading.model;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.sun.istack.NotNull;
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
	@Pattern(regexp = "^[a-zA-Z ]{5,30}", message = "Name min-len:5 max-len:30, only alpahbets and spaces")
	private String patientName;
	
	@Column(name="patient_Address")
	@NotEmpty(message = "Address is empty")
	private String patientAddress;
	
	@Column(name = "Date_of_Birth")
	@NotEmpty(message = "DOB is empty")
	private String dateofbirth;
	
	@Column(name = "patient_Email")
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "Invalid Email ID")
	private String patientEmail;
	
	@Column(name="contact_Number", unique=true)
	@NotNull
	@Pattern(regexp = "^\\d{10}$", message = "Should be a valid 10 digit number")
	private String contactNumber;
	
	@Column(name = "drug_Id")
	@Pattern(regexp = "^[\\d]{5}-[\\d]{4}-[\\d]{2}$", message = "Drug ID should be of format XXXX-XXXX-XX, all digits")
	private String drugId;
	
	@Column(name = "drug_Name")
	@NotEmpty(message = "Drug name is empty")
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
