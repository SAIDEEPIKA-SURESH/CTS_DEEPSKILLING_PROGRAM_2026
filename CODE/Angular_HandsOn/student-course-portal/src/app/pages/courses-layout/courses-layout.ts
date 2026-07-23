import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CourseSummaryWidget } from '../../components/course-summary-widget/course-summary-widget';
import { Notification } from '../../components/notification/notification';

@Component({
  selector: 'app-courses-layout',
  imports: [RouterOutlet, CourseSummaryWidget, Notification],
  templateUrl: './courses-layout.html',
  styleUrl: './courses-layout.css'
})
export class CoursesLayout {}
