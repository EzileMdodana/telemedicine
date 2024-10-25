// src/app/services/appointment.service.ts

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  private baseUrl = 'https://localhost:8080/api/appointments';

  constructor(private http: HttpClient) {}

  bookAppointment(doctorId: string, patientId: string, slotTime: string): Observable<any> {
    return this.http.post(`${this.baseUrl}/book`, { doctorId, patientId, slot: slotTime });
  }

  cancelAppointment(appointmentId: string): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${appointmentId}`);
  }

  rescheduleAppointment(appointmentId: string, newTime: string): Observable<any> {
    return this.http.put(`${this.baseUrl}/${appointmentId}/reschedule`, { newTime });
  }
}