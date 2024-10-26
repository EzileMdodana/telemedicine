package com.example.demo.controllers; // Adjust package name if necessary

import com.example.demo.services.PrescriptionService;
import com.example.demo.models.prescription.Prescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {
    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Prescription>> getPrescriptionsByPatient(@PathVariable Long patientId) {
        List<Prescription> prescriptions = prescriptionService.getAllPrescriptions(patientId);
        return ResponseEntity.ok(prescriptions);
    }

    @PostMapping
public ResponseEntity<Prescription> addPrescription(@RequestBody Prescription prescription) {
    Prescription createdPrescription = prescriptionService.createPrescription(prescription);
    return ResponseEntity.status(201).body(createdPrescription);
}

}
