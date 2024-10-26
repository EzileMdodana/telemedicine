package com.example.demo.services;

import com.example.demo.models.prescription.Prescription;
import java.util.List;

public interface PrescriptionService {
    List<Prescription> getAllPrescriptions(Long patientId);
    Prescription getPrescriptionById(Long prescriptionId);
    Prescription createPrescription(Prescription prescription);
}
