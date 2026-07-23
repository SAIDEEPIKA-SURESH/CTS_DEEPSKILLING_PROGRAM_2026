import { ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { SimpleChange } from '@angular/core';
import { provideMockStore, MockStore } from '@ngrx/store/testing';
import { CourseCard } from './course-card';
import { Course } from '../../models/course.model';

describe('CourseCard', () => {
  let component: CourseCard;
  let fixture: ComponentFixture<CourseCard>;
  let store: MockStore;
  const mockCourse: Course = { 
    id: 1, 
    name: 'Data Structures', 
    code: 'CS101', 
    credits: 4, 
    gradeStatus: 'passed' 
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CourseCard],
      providers: [
        provideMockStore({
          initialState: {
            course: { courses: [], loading: false, error: null },
            enrollment: { enrolledCourseIds: [] }
          }
        })
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(CourseCard);
    component = fixture.componentInstance;
    store = TestBed.inject(MockStore);
    component.course = mockCourse;
    fixture.detectChanges();
  });

  // Step 102
  it('should create', () => {
    expect(component).toBeTruthy();
  });

  // Step 103
  it('should render course details based on @Input', () => {
    fixture.detectChanges();
    const headingText = fixture.debugElement.query(By.css('.course-name')).nativeElement.textContent;
    expect(headingText).toContain('Data Structures');
  });

  // Step 104
  it('should emit enrollRequested event with course ID on enroll click', () => {
    spyOn(component.enrollRequested, 'emit');
    const enrollBtn = fixture.debugElement.query(By.css('.card-actions button:last-child')).nativeElement;
    enrollBtn.click();
    expect(component.enrollRequested.emit).toHaveBeenCalledWith(mockCourse.id);
  });

  // Step 105
  it('should call ngOnChanges and trigger console logging', () => {
    spyOn(console, 'log');
    component.ngOnChanges({
      course: new SimpleChange(null, mockCourse, true)
    });
    expect(console.log).toHaveBeenCalled();
  });
});
