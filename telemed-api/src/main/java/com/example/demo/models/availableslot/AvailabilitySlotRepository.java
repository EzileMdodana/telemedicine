package com.example.demo.models.availableslot;

import com.example.demo.models.doctor.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface AvailabilitySlotRepository extends JpaRepository<AvailabilitySlot, Long> {
    Optional<AvailabilitySlot> findByDoctorAndStartTimeAndStatus(Doctor doctor, LocalDateTime startTime, SlotStatus status);
}
