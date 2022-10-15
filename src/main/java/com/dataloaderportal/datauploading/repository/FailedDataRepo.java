package com.dataloaderportal.datauploading.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dataloaderportal.datauploading.model.InvalidData;

@Repository
public interface FailedDataRepo extends JpaRepository<InvalidData,String> {
    Boolean existsByContactNumber(String contactNumber);
}
