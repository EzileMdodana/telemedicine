package com.example.demo.models.patient;

import com.example.demo.models.appointment.Appointment;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/patients")
@Tag(name = "Patient", description = "Operations related to patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.findAllPatients();
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable UUID id) {
        Patient patient = patientService.getPatientById(id);
        return ResponseEntity.ok(patient);
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        Patient createdPatient = patientService.createPatient(patient);
        return ResponseEntity.ok(createdPatient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable UUID id, @RequestBody Patient updatedPatient) {
        Patient patient = patientService.updatePatient(id, updatedPatient);
        return ResponseEntity.ok(patient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/appointments")
    public ResponseEntity<List<Appointment>> getPatientAppointments(@PathVariable UUID id) {
        List<Appointment> appointments = patientService.getPatientAppointments(id);
        return ResponseEntity.ok(appointments);
    }
}
