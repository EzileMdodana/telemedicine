import { Component, OnInit } from '@angular/core';
import { AppointmentService } from '../services/appointment.service';
import { AlertController, ToastController } from '@ionic/angular';
import { Router } from '@angular/router';

@Component({
  selector: 'app-appointment-booking',
  templateUrl: './appointment-booking.page.html',
  styleUrls: ['./appointment-booking.page.scss'],
})
export class AppointmentBookingPage implements OnInit {

  specializations = [
    'Cardiology',
    'Dermatology',
    'Pediatrics',
    'Neurology',
    'Orthopedics'
];
locations = [
  'Ferndale',
  'Cosmo City',
  'Pretoria CBD',
  'Hyde Park',
  'Rosebank'
];
doctors = [
  {
      id: 'doctor1',
      name: 'Dr. Jane Doe',
      specialization: 'Cardiology',
      location: 'New York',
      availabilitySlots: [
          '2024-10-21T09:00:00',
          '2024-10-21T10:00:00'
      ]
  },
  {
      id: 'doctor2',
      name: 'Dr. John Smith',
      specialization: 'Dermatology',
      location: 'Los Angeles',
      availabilitySlots: [
          '2024-10-21T11:00:00',
          '2024-10-21T12:00:00'
      ]
  },
  {
      id: 'doctor3',
      name: 'Dr. Emily Johnson',
      specialization: 'Pediatrics',
      location: 'Chicago',
      availabilitySlots: [
          '2024-10-21T13:00:00',
          '2024-10-21T14:00:00'
      ]
  },
  {
      id: 'doctor4',
      name: 'Dr. Michael Brown',
      specialization: 'Neurology',
      location: 'Houston',
      availabilitySlots: [
          '2024-10-21T15:00:00',
          '2024-10-21T16:00:00'
      ]
  },
  {
      id: 'doctor5',
      name: 'Dr. Lisa Davis',
      specialization: 'Orthopedics',
      location: 'San Francisco',
      availabilitySlots: [
          '2024-10-21T09:00:00',
          '2024-10-21T10:30:00'
      ]
  }
];
  availableSlots: string[] = [];

  selectedSpecialization: string | undefined;
  selectedLocation: string | undefined;
  selectedDoctorId: string | undefined;
  selectedSlot: string | undefined;

  patientId: string = 'patient-id'; // Replace with actual patient ID

  constructor(
    // private appointmentService: AppointmentService,
    private alertController: AlertController,
    private toastController: ToastController,
    private router: Router,
  ) {}

  ngOnInit() {
    this.loadSpecializations();
    this.loadLocations();
  }

  loadSpecializations() {
    // this.appointmentService.getSpecializations().subscribe(data => {
    //   this.specializations = data;
    // });
  }

  loadLocations() {
    // this.appointmentService.getLocations().subscribe(data => {
    //   this.locations = data;
    // });
  }

  loadDoctors() {
    // if (this.selectedSpecialization && this.selectedLocation) {
    //   this.appointmentService.getDoctorsBySpecializationAndLocation(this.selectedSpecialization, this.selectedLocation)
    //     .subscribe(data => {
    //       this.doctors = data;
    //     });
    // }
  }

  loadAvailableSlots() {
    if (this.selectedDoctorId) {
      const selectedDoctor = this.doctors.find(doctor => doctor.id === this.selectedDoctorId);
      this.availableSlots = selectedDoctor ? selectedDoctor.availabilitySlots : [];
    }
  }

  async bookAppointment() {
    const toast = await this.toastController.create({
      message: 'Appointment booked successfully!',
      duration: 2000,
      color: 'success'
    });
    await toast.present();

    // Navigate to home page after showing toast
    toast.onDidDismiss().then(() => {
      this.router.navigate(['']);
    });
    // this.appointmentService.bookAppointment(this.selectedDoctorId, this.patientId, this.selectedSlot)
    //   .subscribe({
    //     next: async () => {
    //       const alert = await this.alertController.create({
    //         header: 'Success',
    //         message: 'Appointment booked successfully!',
    //         buttons: ['OK']
    //       });
    //       await alert.present();
    //     },
    //     error: async () => {
    //       const alert = await this.alertController.create({
    //         header: 'Error',
    //         message: 'Failed to book appointment. Please try again.',
    //         buttons: ['OK']
    //       });
    //       await alert.present();
    //     }
    //   });
  }

}
