import { Component } from '@angular/core';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-about',
  imports: [DatePipe],
  templateUrl: './about.component.html',
  styleUrl: './about.component.css'
})
export class AboutComponent {
  // Obiekt Date pobierający aktualny dzień i godzinę
  currentDate: Date = new Date();
}