package com.example.demo.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.example.demo.services.MedicalRecordService;

import org.springframework.web.bind.annotation.RequestBody; // Correct import


import com.example.demo.models.medical.MedicalRecord;

@RestController
@RequestMapping("/api/medical-records")
public class MedicalRecordController {
    @Autowired
    private MedicalRecordService medicalRecordService;

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<MedicalRecord>> getRecordsByPatient(@PathVariable Long patientId) {
        List<MedicalRecord> records = medicalRecordService.getAllMedicalRecords(patientId); // Updated method name
        return ResponseEntity.ok(records);
    }
     // New method for adding a medical record
    @PostMapping
    public ResponseEntity<MedicalRecord> addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        MedicalRecord createdRecord = medicalRecordService.createMedicalRecord(medicalRecord);
        return ResponseEntity.status(201).body(createdRecord);  // Return 201 Created status
    }


    // New endpoint to get records by patient ID number
    @GetMapping("/search")
    public ResponseEntity<List<MedicalRecord>> getRecordsByPatientIdNumber(@RequestParam String idNumber) {
        List<MedicalRecord> records = medicalRecordService.getMedicalRecordsByPatientIdNumber(idNumber);
        return ResponseEntity.ok(records);
    }
    
}

