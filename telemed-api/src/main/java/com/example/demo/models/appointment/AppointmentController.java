package com.example.demo.models.appointment;

import com.example.demo.models.shared.RealTimeUpdateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/appointments")
@Tag(name = "Appointment", description = "Operations related to appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private RealTimeUpdateService realTimeUpdateService;

    @PostMapping("/book")
    public ResponseEntity<Appointment> bookAppointment(@RequestParam UUID doctorId, @RequestParam UUID patientId, @RequestParam LocalDateTime slot) {
        Appointment appointment = appointmentService.bookAppointment(doctorId, patientId, slot);
        realTimeUpdateService.notifyAppointmentChange(doctorId, appointment);
        return ResponseEntity.ok(appointment);
    }

    @GetMapping("/patient/{id}")
    public List<Appointment> getPatientAppointments(@PathVariable UUID id) {
        return appointmentService.getPatientAppointments(id);
    }

    @GetMapping("/doctor/{id}")
    public List<Appointment> getDoctorAppointments(@PathVariable UUID id) {
        return appointmentService.getDoctorAppointments(id);
    }

    @PutMapping("/{id}/reschedule")
    public ResponseEntity<Void> rescheduleAppointment(@PathVariable UUID id, @RequestParam LocalDateTime newTime) {
        appointmentService.rescheduleAppointment(id, newTime);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelAppointment(@PathVariable UUID id) {
        appointmentService.cancelAppointment(id);
        return ResponseEntity.ok().build();
    }
}

