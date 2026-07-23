import { Component, OnInit } from '@angular/core';
import { NgIf, NgFor, AsyncPipe } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { Course } from '../../models/course.model';
import { CourseCard } from '../../components/course-card/course-card';
import { loadCourses } from '../../store/course/course.actions';
import { selectAllCourses, selectCoursesLoading, selectCoursesError } from '../../store/course/course.selectors';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-course-list',
  imports: [NgIf, NgFor, AsyncPipe, CourseCard, FormsModule],
  templateUrl: './course-list.html',
  styleUrl: './course-list.css'
})
export class CourseList implements OnInit {
  courses$: Observable<Course[]>;
  isLoading$: Observable<boolean>;
  error$: Observable<string | null>;

  // Local state for search and selection
  searchTerm = '';
  selectedCourseId: number | null = null;
  filteredCourses: Course[] = [];
  allCourses: Course[] = [];

  constructor(
    private store: Store,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.courses$ = this.store.select(selectAllCourses);
    this.isLoading$ = this.store.select(selectCoursesLoading);
    this.error$ = this.store.select(selectCoursesError);
  }

  ngOnInit(): void {
    // Dispatch NgRx load action
    this.store.dispatch(loadCourses());

    // Read query parameter on init
    const searchParam = this.route.snapshot.queryParamMap.get('search');
    if (searchParam) {
      this.searchTerm = searchParam;
    }

    // Subscribe to courses$ to apply local filtering
    this.courses$.subscribe(courses => {
      this.allCourses = courses;
      this.applyFilter();
    });
  }

  onSearchChange(): void {
    // Update query parameters in URL
    this.router.navigate(['/courses'], {
      queryParams: { search: this.searchTerm || null },
      queryParamsHandling: 'merge'
    });
    this.applyFilter();
  }

  applyFilter(): void {
    if (!this.searchTerm.trim()) {
      this.filteredCourses = this.allCourses;
    } else {
      const term = this.searchTerm.toLowerCase();
      this.filteredCourses = this.allCourses.filter(
        c => c.name.toLowerCase().includes(term) || c.code.toLowerCase().includes(term)
      );
    }
  }

  onEnroll(courseId: number): void {
    console.log('Enrolling in course:', courseId);
    this.selectedCourseId = courseId;
  }

  navigateToDetail(courseId: number): void {
    this.router.navigate(['/courses', courseId]);
  }

  /*
   * WHY trackBy IMPROVES PERFORMANCE:
   *
   * By default, when Angular updates a list rendered with *ngFor, it destroys and recreates 
   * the DOM elements for all items in the list. This can be very slow for large lists.
   * By providing a trackBy function, we tell Angular how to uniquely identify each item (e.g. by its id).
   * Angular will then compare the IDs between the old and new arrays. It will only update the DOM 
   * for items that actually changed, leaving unchanged items untouched, which saves substantial CPU cycles 
   * and DOM rendering time.
   */
  trackByCourseId(index: number, course: Course): number {
    return course.id;
  }
}
