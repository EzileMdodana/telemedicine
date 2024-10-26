package com.example.demo.models.doctor;

import com.example.demo.models.availableslot.AvailabilitySlot;
import com.example.demo.models.availableslot.AvailabilitySlotRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AvailabilitySlotRepository availabilitySlotRepository;

    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public List<Doctor> findAllDoctors() {
        return doctorRepository.findAll();
    }

    public List<Doctor> searchDoctors(String specialization, String location) {
        return doctorRepository.findBySpecializationAndLocation(specialization, location);
    }

    public List<AvailabilitySlot> getAvailableSlots(UUID doctorId) {
        return doctorRepository.findAvailableSlotsById(doctorId);
    }

    public void updateAvailability(UUID doctorId, List<AvailabilitySlot> slots) {
        // Update doctor availability logic here
    }

    public Doctor findDoctorById(UUID doctorId) {
        return doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found with ID: " + doctorId));
    }

    public List<AvailabilitySlot> updateDoctorAvailability(UUID doctorId, List<AvailabilitySlot> newSlots) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));

        availabilitySlotRepository.deleteAll(doctor.getAvailabilitySlots());

        newSlots.forEach(slot -> slot.setDoctor(doctor));
        List<AvailabilitySlot> updatedSlots = availabilitySlotRepository.saveAll(newSlots);

        return updatedSlots;
    }
}
