import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-home',
  imports: [FormsModule, RouterLink],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home implements OnInit, OnDestroy {
  portalName = 'Student Course Portal';
  isPortalActive = true;
  message = '';
  searchTerm = '';
  coursesCount = 12; // Will be dynamic later in service integrations
  enrolledCount = 3;
  gpa = 3.8;

  constructor() {}

  ngOnInit(): void {
    // Simulate loading courses
    console.log('HomeComponent initialised — courses loaded');
  }

  ngOnDestroy(): void {
    console.log('HomeComponent destroyed');
  }

  onEnrollClick(): void {
    this.message = 'Enrollment opened!';
  }

  /*
   * DIFFERENCE BETWEEN [property] AND [(ngModel)]:
   *
   * [property] (Property Binding): 
   * It is a one-way data binding. Data flows from the component class (source) to the DOM (target).
   * Changing the value of the property in the TS class will update the DOM, but user edits in the DOM 
   * will NOT automatically update the TS class property.
   * Example: [disabled]="!isPortalActive"
   *
   * [(ngModel)] (Two-way Data Binding):
   * It is a two-way data binding. Data flows in both directions: component class <--> DOM.
   * Changing the value of the property in the TS class updates the DOM element, and conversely, 
   * when the user inputs values in the DOM element (e.g. typing in an input field), the TS class 
   * property is updated automatically in real-time.
   * Under the hood, [(ngModel)]="prop" is shorthand for [ngModel]="prop" (ngModelChange)="prop=$event".
   */
}
