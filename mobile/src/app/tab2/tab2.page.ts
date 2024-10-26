import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AlertController, ToastController } from '@ionic/angular';

@Component({
  selector: 'app-tab2',
  templateUrl: 'tab2.page.html',
  styleUrls: ['tab2.page.scss']
})
export class Tab2Page {
  appointments = [
    {
      id: '1',
      doctorName: 'Dr. Jane Doe',
      specialization: 'Cardiology',
      date: '31 Oct 2024',
      time: '09:00 AM',
      location: 'Cosmo City'
    },
    {
      id: '2',
      doctorName: 'Dr. John Smith',
      specialization: 'Dermatology',
      date: '20 Nov 2024',
      time: '11:00 AM',
      location: 'Ferndale, Randburg'
    },
    {
      id: '3',
      doctorName: 'Dr. Emily Johnson',
      specialization: 'Pediatrics',
      date: '05 Nov 2024',
      time: '01:00 PM',
      location: 'Pretoria CBD'
    }
  ];

  constructor(
    private router: Router, 
    private alertController: AlertController, 
    private toastController: ToastController
  ) {}

  goToAppointmentBooking() {
    this.router.navigate(['/appointment-booking']);
  }

  async rescheduleAppointment(appointmentId: string) {
    const alert = await this.alertController.create({
      header: 'Reschedule Appointment',
      message: 'Please select a new date and time.',
      inputs: [
        { name: 'date', type: 'date', placeholder: 'Select Date' },
        { name: 'time', type: 'time', placeholder: 'Select Time' }
      ],
      buttons: [
        {
          text: 'Cancel',
          role: 'cancel'
        },
        {
          text: 'Reschedule',
          handler: async (data) => {
            // Mock reschedule logic
            const toast = await this.toastController.create({
              message: `Appointment rescheduled to ${data.date} at ${data.time}`,
              duration: 2000,
              color: 'success'
            });
            await toast.present();
          }
        }
      ]
    });

    await alert.present();
  }

  async cancelAppointment(appointmentId: string) {
    const alert = await this.alertController.create({
      header: 'Cancel Appointment',
      message: 'Are you sure you want to cancel this appointment?',
      buttons: [
        {
          text: 'No',
          role: 'cancel'
        },
        {
          text: 'Yes, Cancel',
          handler: async () => {
            // Mock cancellation logic
            const toast = await this.toastController.create({
              message: 'Appointment canceled successfully',
              duration: 2000,
              color: 'danger'
            });
            await toast.present();
          }
        }
      ]
    });

    await alert.present();
  }

  goToProfile() {
    this.router.navigate(['/profile']);
  }
}
