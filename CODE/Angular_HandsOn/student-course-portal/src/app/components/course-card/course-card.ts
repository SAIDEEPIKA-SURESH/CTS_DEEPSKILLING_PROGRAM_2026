import { Component, Input, Output, EventEmitter, OnChanges, SimpleChanges } from '@angular/core';
import { NgIf, NgClass, NgStyle, NgSwitch, NgSwitchCase, NgSwitchDefault } from '@angular/common';
import { Course } from '../../models/course.model';
import { EnrollmentService } from '../../services/enrollment.service';
import { Highlight } from '../../directives/highlight';
import { CreditLabelPipe } from '../../pipes/credit-label-pipe';

@Component({
  selector: 'app-course-card',
  imports: [NgIf, NgClass, NgStyle, NgSwitch, NgSwitchCase, NgSwitchDefault, Highlight, CreditLabelPipe],
  templateUrl: './course-card.html',
  styleUrl: './course-card.css'
})
export class CourseCard implements OnChanges {
  @Input({ required: true }) course!: Course;
  @Input() highlightColor: string = 'yellow';
  @Output() enrollRequested = new EventEmitter<number>();

  isExpanded = false;

  constructor(private enrollmentService: EnrollmentService) {}

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['course']) {
      const prev = changes['course'].previousValue;
      const curr = changes['course'].currentValue;
      console.log(`CourseCard [${this.course?.code}] changed - Previous:`, prev, 'Current:', curr);
    }
  }

  get isEnrolled(): boolean {
    return this.enrollmentService.isEnrolled(this.course.id);
  }

  toggleDetails(): void {
    this.isExpanded = !this.isExpanded;
  }

  onEnrollToggle(): void {
    if (this.isEnrolled) {
      this.enrollmentService.unenroll(this.course.id);
    } else {
      this.enrollmentService.enroll(this.course.id);
    }
    this.enrollRequested.emit(this.course.id);
  }

  /*
   * GETTER FOR CARD CLASSES:
   *
   * Using a getter in the TypeScript class is a best practice for clean templates.
   * It separates the business logic of style resolution from the presentation markup.
   * Instead of writing complex conditional objects directly in the HTML (which makes the HTML cluttered 
   * and harder to maintain/read), we define it in the component class and bind it in the template as [ngClass]="cardClasses".
   * This also makes the class logic easier to unit test independently.
   */
  get cardClasses() {
    return {
      'card--enrolled': this.isEnrolled,
      'card--full': this.course.credits >= 4,
      'expanded': this.isExpanded
    };
  }
}
