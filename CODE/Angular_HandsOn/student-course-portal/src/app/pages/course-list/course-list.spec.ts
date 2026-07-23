import { ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { provideMockStore, MockStore } from '@ngrx/store/testing';
import { CourseList } from './course-list';
import { Course } from '../../models/course.model';

describe('CourseList', () => {
  let component: CourseList;
  let fixture: ComponentFixture<CourseList>;
  let store: MockStore;
  let mockRouter: any;
  let mockActivatedRoute: any;

  const mockCourses: Course[] = [
    { id: 1, name: 'Data Structures', code: 'CS101', credits: 4, gradeStatus: 'passed' },
    { id: 2, name: 'Database Management', code: 'DB102', credits: 3, gradeStatus: 'pending' }
  ];

  beforeEach(async () => {
    mockRouter = jasmine.createSpyObj('Router', ['navigate']);
    mockActivatedRoute = {
      snapshot: {
        queryParamMap: {
          get: (key: string) => null
        }
      }
    };

    await TestBed.configureTestingModule({
      imports: [CourseList],
      providers: [
        provideMockStore({
          initialState: {
            course: { courses: mockCourses, loading: false, error: null },
            enrollment: { enrolledCourseIds: [] }
          }
        }),
        { provide: Router, useValue: mockRouter },
        { provide: ActivatedRoute, useValue: mockActivatedRoute }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(CourseList);
    component = fixture.componentInstance;
    store = TestBed.inject(MockStore);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  // Step 109
  it('should render course cards matching initial store state', () => {
    fixture.detectChanges();
    const cards = fixture.debugElement.queryAll(By.css('app-course-card'));
    expect(cards.length).toBe(2);
  });

  // Step 110
  it('should display loading indicator when store loading is true', () => {
    // Simulate loading state (Step 110)
    store.setState({
      course: { courses: [], loading: true, error: null },
      enrollment: { enrolledCourseIds: [] }
    });
    fixture.detectChanges();
    
    const loadingEl = fixture.debugElement.query(By.css('.loading-state'));
    expect(loadingEl).toBeTruthy();
    
    const textEl = loadingEl.nativeElement.textContent;
    expect(textEl).toContain('Loading course catalog...');
  });
});
