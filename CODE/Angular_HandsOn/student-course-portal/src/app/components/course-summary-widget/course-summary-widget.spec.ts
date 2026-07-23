import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CourseSummaryWidget } from './course-summary-widget';
import { provideMockStore } from '@ngrx/store/testing';

describe('CourseSummaryWidget', () => {
  let component: CourseSummaryWidget;
  let fixture: ComponentFixture<CourseSummaryWidget>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CourseSummaryWidget],
      providers: [
        provideMockStore({
          initialState: {
            course: { courses: [], loading: false, error: null },
            enrollment: { enrolledCourseIds: [] }
          }
        })
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(CourseSummaryWidget);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
