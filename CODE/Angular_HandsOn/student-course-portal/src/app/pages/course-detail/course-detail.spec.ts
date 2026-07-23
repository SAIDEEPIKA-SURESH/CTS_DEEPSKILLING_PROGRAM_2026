import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CourseDetail } from './course-detail';
import { provideRouter, ActivatedRoute } from '@angular/router';
import { provideHttpClient } from '@angular/common/http';
import { provideHttpClientTesting } from '@angular/common/http/testing';

describe('CourseDetail', () => {
  let component: CourseDetail;
  let fixture: ComponentFixture<CourseDetail>;
  let mockActivatedRoute: any;

  beforeEach(async () => {
    mockActivatedRoute = {
      snapshot: {
        paramMap: {
          get: (key: string) => '1'
        }
      }
    };

    await TestBed.configureTestingModule({
      imports: [CourseDetail],
      providers: [
        provideRouter([]),
        { provide: ActivatedRoute, useValue: mockActivatedRoute },
        provideHttpClient(),
        provideHttpClientTesting()
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(CourseDetail);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
