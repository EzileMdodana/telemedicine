package com.example.demo.models.doctor;

import com.example.demo.models.availableslot.AvailabilitySlot;
import com.example.demo.models.shared.RealTimeUpdateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/doctors")
@Tag(name = "Doctor", description = "Operations related to doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private RealTimeUpdateService realTimeUpdateService;

    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        Doctor createdDoctor = doctorService.createDoctor(doctor);
        return ResponseEntity.ok(createdDoctor);
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = doctorService.findAllDoctors();
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/search")
    public List<Doctor> searchDoctors(@RequestParam String specialization, @RequestParam String location) {
        return doctorService.searchDoctors(specialization, location);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable UUID id) {
        Doctor doctor = doctorService.findDoctorById(id);
        return ResponseEntity.ok(doctor);
    }

    @GetMapping("/{id}/availability")
    public List<AvailabilitySlot> getAvailability(@PathVariable UUID id) {
        return doctorService.getAvailableSlots(id);
    }

    @PutMapping("/{id}/availability")
    public ResponseEntity<Void> updateAvailability(@PathVariable UUID id, @RequestBody List<AvailabilitySlot> slots) {
        doctorService.updateAvailability(id, slots);
        realTimeUpdateService.notifyAvailabilityChange(id, slots);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/set/availability")
    public ResponseEntity<List<AvailabilitySlot>> updateDoctorAvailability(
            @PathVariable UUID id, @RequestBody List<AvailabilitySlot> newSlots) {
        List<AvailabilitySlot> updatedSlots = doctorService.updateDoctorAvailability(id, newSlots);
        return ResponseEntity.ok(updatedSlots);
    }
}
