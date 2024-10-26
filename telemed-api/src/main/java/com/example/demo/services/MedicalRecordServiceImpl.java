package com.example.demo.services;

import com.example.demo.models.medical.MedicalRecord;
import com.example.demo.repositories.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;

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
    
    
}
