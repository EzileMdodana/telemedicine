package com.example.demo.repositories;

import com.example.demo.models.prescription.Prescription;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    // Custom query methods can go here if needed
    List<Prescription> findByPatientId(Long patientId);
}
