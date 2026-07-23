import { CanDeactivateFn } from '@angular/router';
import { ReactiveEnrollment } from '../pages/reactive-enrollment/reactive-enrollment';

export const unsavedChangesGuard: CanDeactivateFn<ReactiveEnrollment> = (component) => {
  // If the form exists and is dirty, prompt confirmation (Hands-On 7 step 77)
  if (component && component.enrollForm && component.enrollForm.dirty && !component.submitted) {
    return window.confirm('You have unsaved changes. Leave?');
  }
  return true;
};
