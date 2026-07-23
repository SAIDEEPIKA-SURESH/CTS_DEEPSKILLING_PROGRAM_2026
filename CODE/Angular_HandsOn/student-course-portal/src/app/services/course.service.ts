import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { map, catchError, tap, retry } from 'rxjs/operators';
import { Course } from '../models/course.model';

@Injectable({
  providedIn: 'root'
})
export class CourseService {
  private apiUrl = 'http://localhost:3000/courses';

  constructor(private http: HttpClient) {}

  getCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(this.apiUrl).pipe(
      // Retry failed requests up to 2 times before throwing error
      retry(2),
      // Transform response - only keep courses with credits > 0 (Hands-On 8 task 2 step 83)
      map(courses => courses.filter(c => c.credits > 0)),
      // Tap for logging side effects (Hands-On 8 task 2 step 85)
      tap(courses => console.log(`Courses loaded: ${courses.length}`)),
      // Error handling (Hands-On 8 task 2 step 84)
      catchError(err => {
        console.error('API Error in CourseService:', err);
        return throwError(() => new Error('Failed to load courses. Please try again.'));
      })
    );
  }

  getCourseById(id: number | string): Observable<Course> {
    return this.http.get<Course>(`${this.apiUrl}/${id}`).pipe(
      retry(2),
      tap(course => console.log(`Fetched course details: ${course.name}`)),
      catchError(err => {
        console.error(`Error fetching course ID ${id}:`, err);
        return throwError(() => new Error(`Failed to load details for course ID ${id}.`));
      })
    );
  }

  createCourse(course: Omit<Course, 'id'>): Observable<Course> {
    return this.http.post<Course>(this.apiUrl, course).pipe(
      tap(newCourse => console.log(`Created course: ${newCourse.name} (ID: ${newCourse.id})`)),
      catchError(err => {
        console.error('Error creating course:', err);
        return throwError(() => new Error('Failed to create course. Please verify inputs.'));
      })
    );
  }

  updateCourse(course: Course): Observable<Course> {
    return this.http.put<Course>(`${this.apiUrl}/${course.id}`, course).pipe(
      tap(updated => console.log(`Updated course ID ${updated.id}`)),
      catchError(err => {
        console.error(`Error updating course ID ${course.id}:`, err);
        return throwError(() => new Error(`Failed to update course ID ${course.id}.`));
      })
    );
  }

  deleteCourse(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`).pipe(
      tap(() => console.log(`Deleted course ID ${id}`)),
      catchError(err => {
        console.error(`Error deleting course ID ${id}:`, err);
        return throwError(() => new Error(`Failed to delete course ID ${id}.`));
      })
    );
  }
}

