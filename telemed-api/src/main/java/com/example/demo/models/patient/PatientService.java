package com.example.demo.models.patient;

import com.example.demo.models.appointment.Appointment;
import com.example.demo.models.appointment.AppointmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    // Retrieve a patient by ID
    public Patient getPatientById(UUID patientId) {
        return patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));
    }

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public List<Patient> findAllPatients() {
        return patientRepository.findAll();
    }

    public Patient updatePatient(UUID patientId, Patient updatedPatient) {
        Patient patient = getPatientById(patientId);
        patient.setFirstName(updatedPatient.getFirstName());
        patient.setLastName(updatedPatient.getLastName());
        patient.setEmailAddress(updatedPatient.getEmailAddress());
        return patientRepository.save(patient);
    }

    public void deletePatient(UUID patientId) {
        Patient patient = getPatientById(patientId);
        patientRepository.delete(patient);
    }

    // Retrieve all appointments for a patient
    public List<Appointment> getPatientAppointments(UUID patientId) {
        Patient patient = getPatientById(patientId);
        return appointmentRepository.findByPatientId(patientId);
    }
}
