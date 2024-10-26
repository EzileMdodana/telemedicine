package com.example.demo.services;



import java.util.List;

import com.example.demo.models.patient.Patient;

public interface PatientService {
    List<Patient> getAllPatients();
    Patient getPatientById(Long patientId);
    Patient createPatient(Patient patient);
}
