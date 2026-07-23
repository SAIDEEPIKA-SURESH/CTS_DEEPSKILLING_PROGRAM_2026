import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CoursesLayout } from './courses-layout';
import { provideRouter } from '@angular/router';
import { provideMockStore } from '@ngrx/store/testing';

describe('CoursesLayout', () => {
  let component: CoursesLayout;
  let fixture: ComponentFixture<CoursesLayout>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CoursesLayout],
      providers: [
        provideRouter([]),
        provideMockStore({})
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(CoursesLayout);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
