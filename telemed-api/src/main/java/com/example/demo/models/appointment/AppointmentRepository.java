package com.example.demo.models.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    List<Appointment> findByPatientId(UUID patient_id);
    List<Appointment> findByDoctorId(UUID doctor_id);
}
