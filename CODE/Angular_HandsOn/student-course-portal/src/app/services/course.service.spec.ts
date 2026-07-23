import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';
import { CourseService } from './course.service';
import { Course } from '../models/course.model';

describe('CourseService', () => {
  let service: CourseService;
  let httpMock: HttpTestingController;

  const mockCourses: Course[] = [
    { id: 1, name: 'CS 1', code: 'CS101', credits: 3, gradeStatus: 'passed' },
    { id: 2, name: 'CS 2', code: 'CS201', credits: 4, gradeStatus: 'pending' }
  ];

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        CourseService,
        provideHttpClient(),
        provideHttpClientTesting()
      ]
    });
    service = TestBed.inject(CourseService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    // Assert no outstanding/unfulfilled requests (Step 107 hint)
    httpMock.verify();
  });

  // Step 107
  it('should fetch all courses using getCourses() and filter credits > 0', () => {
    service.getCourses().subscribe(courses => {
      expect(courses.length).toBe(2);
      expect(courses[0].code).toBe('CS101');
    });

    const req = httpMock.expectOne('http://localhost:3000/courses');
    expect(req.request.method).toBe('GET');
    req.flush(mockCourses);
  });

  // Step 108
  it('should handle error when API returns a 500 status code', () => {
    service.getCourses().subscribe({
      next: () => fail('Should have failed with 500 error'),
      error: (err) => {
        expect(err.message).toContain('Failed to load courses. Please try again.');
      }
    });

    // 1st request
    const req1 = httpMock.expectOne('http://localhost:3000/courses');
    req1.flush('Error 1', { status: 500, statusText: 'Server Error' });

    // 2nd request (1st retry)
    const req2 = httpMock.expectOne('http://localhost:3000/courses');
    req2.flush('Error 2', { status: 500, statusText: 'Server Error' });

    // 3rd request (2nd retry)
    const req3 = httpMock.expectOne('http://localhost:3000/courses');
    req3.flush('Error 3', { status: 500, statusText: 'Server Error' });
  });

});
