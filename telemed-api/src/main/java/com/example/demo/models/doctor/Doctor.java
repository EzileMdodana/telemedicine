package com.example.demo.models.doctor;

import com.example.demo.models.appointment.Appointment;
import com.example.demo.models.availableslot.AvailabilitySlot;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Doctor {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String specialization;
    @Column(nullable = false)
    private String location;

    // Availability: List of available slots
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<AvailabilitySlot> availabilitySlots;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Appointment> appointments;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<AvailabilitySlot> getAvailabilitySlots() {
        return availabilitySlots;
    }

    public void setAvailabilitySlots(List<AvailabilitySlot> availabilitySlots) {
        this.availabilitySlots = availabilitySlots;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public UUID getId() {
        return id;
    }
}
