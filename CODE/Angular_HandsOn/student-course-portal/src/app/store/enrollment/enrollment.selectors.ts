import { createFeatureSelector, createSelector } from '@ngrx/store';
import { EnrollmentState } from './enrollment.reducer';
import { selectAllCourses } from '../course/course.selectors';

export const selectEnrollmentState = createFeatureSelector<EnrollmentState>('enrollment');

export const selectEnrolledIds = createSelector(
  selectEnrollmentState,
  (state) => state.enrolledCourseIds
);

/*
 * CROSS-SLICE SELECTOR (NgRx pattern):
 *
 * This selector combines courses from the course store slice and enrolledCourseIds from the 
 * enrollment store slice. It filters and returns full Course objects for all enrolled courses.
 * This prevents data duplication and keeps the store normalized.
 */
export const selectEnrolledCourses = createSelector(
  selectAllCourses,
  selectEnrolledIds,
  (courses, enrolledIds) => courses.filter(course => enrolledIds.includes(course.id))
);
