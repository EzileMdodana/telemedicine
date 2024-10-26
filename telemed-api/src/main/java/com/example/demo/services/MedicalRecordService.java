package com.example.demo.services;

import com.example.demo.models.medical.MedicalRecord;
import java.util.List;

public interface MedicalRecordService {
    List<MedicalRecord> getAllMedicalRecords(Long patientId);
    MedicalRecord getMedicalRecordById(Long recordId);
    MedicalRecord createMedicalRecord(MedicalRecord medicalRecord);
}
