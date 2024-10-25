package com.example.demo.models.doctor;

import com.example.demo.models.availableslot.AvailabilitySlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
    List<Doctor> findBySpecializationAndLocation(String specialization, String location);
    List<AvailabilitySlot> findAvailableSlotsById(UUID id);
}
