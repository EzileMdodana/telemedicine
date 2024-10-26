package com.example.demo.models.appointment;

import com.example.demo.models.availableslot.AvailabilitySlot;
import com.example.demo.models.availableslot.AvailabilitySlotRepository;
import com.example.demo.models.availableslot.SlotStatus;
import com.example.demo.models.doctor.Doctor;
import com.example.demo.models.doctor.DoctorRepository;
import com.example.demo.models.patient.Patient;
import com.example.demo.models.patient.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AvailabilitySlotRepository availabilitySlotRepository;

    public Appointment bookAppointment(UUID doctorId, UUID patientId, LocalDateTime slotTime) {
        //Find the doctor and patient
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));

        //Check for an available slot for the specified time
        AvailabilitySlot slot = availabilitySlotRepository.findByDoctorAndStartTimeAndStatus(
                doctor, slotTime, SlotStatus.AVAILABLE
        ).orElseThrow(() -> new IllegalArgumentException("Selected time slot is not available"));

        //Create and save the appointment
        Appointment newAppointment = new Appointment();
        newAppointment.setDoctor(doctor);
        newAppointment.setPatient(patient);
        newAppointment.setAppointmentTime(slotTime);
        newAppointment.setStatus(AppointmentStatus.SCHEDULED);

        Appointment savedAppointment = appointmentRepository.save(newAppointment);

        //Update slot status to BOOKED and save
        slot.setStatus(SlotStatus.BOOKED);
        availabilitySlotRepository.save(slot);

        return savedAppointment;
    }

    public List<Appointment> getPatientAppointments(UUID patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    public List<Appointment> getDoctorAppointments(UUID doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    public void rescheduleAppointment(UUID appointmentId, LocalDateTime newTime) {
        //Find the existing appointment
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));

        //Check if the new slot is available
        Doctor doctor = appointment.getDoctor();
        AvailabilitySlot newSlot = availabilitySlotRepository.findByDoctorAndStartTimeAndStatus(
                doctor, newTime, SlotStatus.AVAILABLE
        ).orElseThrow(() -> new IllegalArgumentException("New time slot is not available"));

        //Update the current slot's status back to AVAILABLE
        AvailabilitySlot currentSlot = availabilitySlotRepository.findByDoctorAndStartTimeAndStatus(
                doctor, appointment.getAppointmentTime(), SlotStatus.BOOKED
        ).orElseThrow(() -> new IllegalStateException("Current booked slot not found"));
        currentSlot.setStatus(SlotStatus.AVAILABLE);
        availabilitySlotRepository.save(currentSlot);

        //Update the appointment time and book the new slot
        appointment.setAppointmentTime(newTime);
        appointmentRepository.save(appointment);

        //Mark the new slot as BOOKED
        newSlot.setStatus(SlotStatus.BOOKED);
        availabilitySlotRepository.save(newSlot);
    }

    public void cancelAppointment(UUID appointmentId) {
        //Find the appointment
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));

        //Update appointment status to CANCELED
        appointment.setStatus(AppointmentStatus.CANCELED);
        appointmentRepository.save(appointment);

        //Mark the slot as AVAILABLE
        Doctor doctor = appointment.getDoctor();
        LocalDateTime appointmentTime = appointment.getAppointmentTime();
        AvailabilitySlot slot = availabilitySlotRepository.findByDoctorAndStartTimeAndStatus(
                doctor, appointmentTime, SlotStatus.BOOKED
        ).orElseThrow(() -> new IllegalStateException("Booked slot not found"));
        slot.setStatus(SlotStatus.AVAILABLE);
        availabilitySlotRepository.save(slot);
    }
}

