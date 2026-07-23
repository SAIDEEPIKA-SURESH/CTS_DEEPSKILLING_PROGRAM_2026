import { Component, OnInit } from '@angular/core';
import { AsyncPipe, NgIf } from '@angular/common';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { selectAllCourses } from '../../store/course/course.selectors';
import { Course } from '../../models/course.model';

@Component({
  selector: 'app-course-summary-widget',
  imports: [AsyncPipe, NgIf],
  templateUrl: './course-summary-widget.html',
  styleUrl: './course-summary-widget.css'
})
export class CourseSummaryWidget implements OnInit {
  courses$: Observable<Course[]>;

  constructor(private store: Store) {
    this.courses$ = this.store.select(selectAllCourses);
  }

  ngOnInit(): void {}
}
