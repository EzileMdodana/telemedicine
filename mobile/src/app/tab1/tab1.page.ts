import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tab1',
  templateUrl: 'tab1.page.html',
  styleUrls: ['tab1.page.scss']
})
export class HomePage {
  upcomingAppointment: any;
  constructor(private router: Router,) {
    this.upcomingAppointment = {
      doctorName: 'Dr. Jane Doe',
      specialization: 'Cardiology',
      date: '31 October , 2024',
      time: '09:00 AM'
    };
  }

  goToChat() {
    this.router.navigate(['/chat']);
  }

  goToProfile() {
    this.router.navigate(['/profile']);
  }

}
