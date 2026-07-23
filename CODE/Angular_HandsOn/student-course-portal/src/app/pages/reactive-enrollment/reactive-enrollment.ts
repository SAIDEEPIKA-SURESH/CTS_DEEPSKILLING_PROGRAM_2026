import { Component, OnInit } from '@angular/core';
import { NgIf, NgFor } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, FormArray, Validators, AbstractControl, ValidationErrors, FormControl } from '@angular/forms';

// Custom Synchronous Validator (Hands-On 5 step 53)
export function noCourseCode(control: AbstractControl): ValidationErrors | null {
  const value = String(control.value || '');
  if (value.toUpperCase().startsWith('XX')) {
    return { noCourseCode: true };
  }
  return null;
}

// Custom Async Validator (Hands-On 5 step 55)
export function simulateEmailCheck(control: AbstractControl): Promise<ValidationErrors | null> {
  return new Promise((resolve) => {
    setTimeout(() => {
      const email = String(control.value || '');
      if (email.toLowerCase().includes('test@')) {
        resolve({ emailTaken: true });
      } else {
        resolve(null);
      }
    }, 800); // 800ms simulation delay
  });
}

@Component({
  selector: 'app-reactive-enrollment',
  imports: [NgIf, NgFor, ReactiveFormsModule],
  templateUrl: './reactive-enrollment.html',
  styleUrl: './reactive-enrollment.css'
})
export class ReactiveEnrollment implements OnInit {
  enrollForm!: FormGroup;
  submitted = false;

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    // Build the reactive form (Hands-On 5 step 49 & 56)
    this.enrollForm = this.fb.group({
      studentName: ['', [Validators.required, Validators.minLength(3)]],
      studentEmail: ['', {
        validators: [Validators.required, Validators.email],
        asyncValidators: [simulateEmailCheck],
        updateOn: 'blur' // Run async validation on blur for better performance
      }],
      courseId: ['', [Validators.required, noCourseCode]],
      preferredSemester: ['Odd', Validators.required],
      agreeToTerms: [false, Validators.requiredTrue],
      additionalCourses: this.fb.array([])
    });
  }

  /*
   * TYPED GETTER FOR FORM ARRAY:
   *
   * Implementing get additionalCourses() { return this.enrollForm.get('additionalCourses') as FormArray; }
   * provides a strongly-typed reference to the FormArray in our component class.
   *
   * Why this getter is better than casting in the template:
   * 1. Cleaner HTML: In Angular templates, casting with "as FormArray" is verbose, ugly, and error-prone.
   *    Instead of writing `(enrollForm.get('additionalCourses') as FormArray).controls`, we can simply write `additionalCourses.controls`.
   * 2. Type-safety: The TypeScript compiler performs strict type checking on this getter, preventing runtime errors.
   * 3. Refactoring: If the form structure changes, we only need to update the key name in this one getter, rather than everywhere in the HTML template.
   */
  get additionalCourses(): FormArray {
    return this.enrollForm.get('additionalCourses') as FormArray;
  }

  addCourse(): void {
    this.additionalCourses.push(this.fb.control('', Validators.required));
  }

  removeCourse(index: number): void {
    this.additionalCourses.removeAt(index);
  }

  onSubmit(): void {
    if (this.enrollForm.valid) {
      this.submitted = true;
      
      /*
       * DIFFERENCE BETWEEN enrollForm.value AND enrollForm.getRawValue():
       *
       * enrollForm.value:
       * - Excludes any controls that are currently DISABLED. 
       * - If a form control has `disabled: true` set, its key and value will be missing from the resulting object.
       *
       * enrollForm.getRawValue():
       * - Includes the values of ALL controls inside the FormGroup, REGARDLESS of their disabled status.
       * - This is critical when you want to submit all form data to a backend, even if some inputs were disabled/read-only on the UI.
       */
      console.log('Reactive Form Submitted!');
      console.log('Form Value (excluding disabled):', this.enrollForm.value);
      console.log('Form Raw Value (including disabled):', this.enrollForm.getRawValue());
    } else {
      this.enrollForm.markAllAsTouched();
    }
  }

  resetForm(): void {
    this.enrollForm.reset({
      studentName: '',
      studentEmail: '',
      courseId: '',
      preferredSemester: 'Odd',
      agreeToTerms: false
    });
    this.additionalCourses.clear();
    this.submitted = false;
  }
}

