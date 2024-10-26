package com.example.demo.services;

import com.example.demo.models.prescription.Prescription;
import com.example.demo.repositories.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;

    @Autowired
    public PrescriptionServiceImpl(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    @Override
    public List<Prescription> getAllPrescriptions(Long patientId) {
        // Add logic to fetch prescriptions for a specific patient
        return prescriptionRepository.findByPatientId(patientId);
    }

    @Override
    public Prescription getPrescriptionById(Long prescriptionId) {
        return prescriptionRepository.findById(prescriptionId).orElse(null);
    }

    @Override
    public Prescription createPrescription(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }
}
