import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.page.html',
  styleUrls: ['./chat.page.scss'],
})
export class ChatPage implements OnInit {

  messages = [
    { text: 'Hello!', sender: 'patient', timestamp: '08:59 AM' },
    { text: 'Hello! How can I help you today?', sender: 'doctor', timestamp: '09:00 AM' },
    { text: 'I have some questions about my appointment.', sender: 'patient', timestamp: '09:01 AM' },
    { text: 'Sure, feel free to ask!', sender: 'doctor', timestamp: '09:02 AM' }
  ];
  
  newMessage: string = '';

  constructor() {}

  ngOnInit() {}

  sendMessage() {
    if (this.newMessage.trim()) {
      this.messages.push({
        text: this.newMessage,
        sender: 'patient',
        timestamp: new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' })
      });
      this.newMessage = '';
    }
  }

}
