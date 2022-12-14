package com.dataloaderportal.datauploading.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dataloaderportal.datauploading.model.Patient;


@Repository
public interface PatientRepo extends JpaRepository<Patient,String>{
    Boolean existsByContactNumber(String contactNumber);
}
