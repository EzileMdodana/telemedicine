package com.example.demo.repositories;

import com.example.demo.models.medical.MedicalRecord;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    // Custom query methods can go here if needed
    List<MedicalRecord> findByPatientId(Long patientId);
    
}
    