import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-preventive-care',
  templateUrl: './preventive-care.page.html',
  styleUrls: ['./preventive-care.page.scss'],
})
export class PreventiveCarePage implements OnInit {

  constructor(private router: Router,) { }

  ngOnInit() {
  }

  goToChat() {
    this.router.navigate(['/chat']);
  }

  goToProfile() {
    this.router.navigate(['/profile']);
  }

}
