import { Component, OnInit } from '@angular/core';
import { AsyncPipe, NgIf } from '@angular/common';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { Observable } from 'rxjs';
import { Course } from '../../models/course.model';
import { CourseService } from '../../services/course.service';



@Component({
  selector: 'app-course-detail',
  imports: [NgIf, AsyncPipe, RouterLink],
  templateUrl: './course-detail.html',
  styleUrl: './course-detail.css'
})
export class CourseDetail implements OnInit {
  course$!: Observable<Course>;

  constructor(
    private route: ActivatedRoute,
    private courseService: CourseService
  ) {}

  ngOnInit(): void {
    // Read the :id route parameter
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.course$ = this.courseService.getCourseById(Number(id));
    }
  }
}
