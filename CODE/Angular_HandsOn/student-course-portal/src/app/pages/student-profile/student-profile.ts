import { Component, OnInit } from '@angular/core';
import { AsyncPipe, NgIf, NgFor } from '@angular/common';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { Course } from '../../models/course.model';
import { selectEnrolledCourses } from '../../store/enrollment/enrollment.selectors';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-student-profile',
  imports: [NgIf, NgFor, AsyncPipe, RouterLink],
  templateUrl: './student-profile.html',
  styleUrl: './student-profile.css'
})
export class StudentProfile implements OnInit {
  enrolledCourses$: Observable<Course[]>;

  // Static Profile Info
  student = {
    name: 'Sai Deepika',
    id: 'DN-2026-FSD',
    email: 'sai.deepika@nurture.edu',
    track: '.NET Full Stack Engineer',
    gpa: '3.8',
    standing: 'Junior'
  };

  constructor(private store: Store) {
    this.enrolledCourses$ = this.store.select(selectEnrolledCourses);
  }

  ngOnInit(): void {}
}
