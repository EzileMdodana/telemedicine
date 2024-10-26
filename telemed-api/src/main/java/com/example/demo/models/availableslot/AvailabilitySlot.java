package com.example.demo.models.availableslot;

import com.example.demo.models.doctor.Doctor;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class AvailabilitySlot {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Column(nullable = false)
    private LocalDateTime startTime;
    @Column(nullable = false)
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    private SlotStatus status = SlotStatus.AVAILABLE; // AVAILABLE, BOOKED

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public SlotStatus getStatus() {
        return status;
    }

    public void setStatus(SlotStatus status) {
        this.status = status;
    }
}

