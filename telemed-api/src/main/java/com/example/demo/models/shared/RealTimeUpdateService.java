package com.example.demo.models.shared;

import com.example.demo.models.appointment.Appointment;
import com.example.demo.models.availableslot.AvailabilitySlot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RealTimeUpdateService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void notifyAvailabilityChange(UUID doctorId, List<AvailabilitySlot> slots) {
        messagingTemplate.convertAndSend("/topic/availability/" + doctorId, slots);
    }

    public void notifyAppointmentChange(UUID doctorId, Appointment appointment) {
        messagingTemplate.convertAndSend("/topic/appointments/" + doctorId, appointment);
    }
}
