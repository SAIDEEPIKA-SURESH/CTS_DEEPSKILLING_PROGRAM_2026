import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveEnrollment } from './reactive-enrollment';

describe('ReactiveEnrollment', () => {
  let component: ReactiveEnrollment;
  let fixture: ComponentFixture<ReactiveEnrollment>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReactiveEnrollment]
    }).compileComponents();

    fixture = TestBed.createComponent(ReactiveEnrollment);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
