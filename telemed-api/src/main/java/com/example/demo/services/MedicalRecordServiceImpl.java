package com.example.demo.services;

import com.example.demo.models.medical.MedicalRecord;
import com.example.demo.models.patient.Patient;
import com.example.demo.repositories.MedicalRecordRepository;
import com.example.demo.repositories.PatientRepository; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    public MedicalRecordServiceImpl(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @Override
    public List<MedicalRecord> getAllMedicalRecords(Long patientId) {
        // Add your logic to fetch medical records for a specific patient
        return medicalRecordRepository.findByPatientId(patientId);
    }

    @Override
    public MedicalRecord getMedicalRecordById(Long recordId) {
        return medicalRecordRepository.findById(recordId).orElse(null);
    }

    @Override
    public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) {
        System.out.println("Received MedicalRecord: " + medicalRecord);
        return medicalRecordRepository.save(medicalRecord);
    }
    
     @Override
    public List<MedicalRecord> getMedicalRecordsByPatientIdNumber(String idNumber) {
        Patient patient = patientRepository.findByIdNumber(idNumber); // Find patient by ID number
        if (patient != null) {
            return medicalRecordRepository.findByPatientId(patient.getId()); // Get records using patient ID
        }
        return List.of(); // Return an empty list if no patient found
    }
}
