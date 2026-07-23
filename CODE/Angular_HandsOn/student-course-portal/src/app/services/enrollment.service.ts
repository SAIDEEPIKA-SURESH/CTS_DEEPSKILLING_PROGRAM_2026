import { Injectable } from '@angular/core';
import { Store } from '@ngrx/store';
import { enrollInCourse, unenrollFromCourse } from '../store/enrollment/enrollment.actions';
import { selectEnrolledIds } from '../store/enrollment/enrollment.selectors';

@Injectable({
  providedIn: 'root'
})
export class EnrollmentService {
  private enrolledCourseIds: number[] = [];

  constructor(private store: Store) {
    // Sync local array with store state
    this.store.select(selectEnrolledIds).subscribe(ids => {
      this.enrolledCourseIds = ids;
    });
  }

  enroll(courseId: number): void {
    this.store.dispatch(enrollInCourse({ courseId }));
  }

  unenroll(courseId: number): void {
    this.store.dispatch(unenrollFromCourse({ courseId }));
  }

  isEnrolled(courseId: number): boolean {
    return this.enrolledCourseIds.includes(courseId);
  }

  getEnrolledCourseIds(): number[] {
    return this.enrolledCourseIds;
  }
}
