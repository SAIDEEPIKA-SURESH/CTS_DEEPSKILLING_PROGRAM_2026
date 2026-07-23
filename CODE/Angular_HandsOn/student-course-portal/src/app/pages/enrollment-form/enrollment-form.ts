import { Component } from '@angular/core';
import { NgIf } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';

@Component({
  selector: 'app-enrollment-form',
  imports: [NgIf, FormsModule],
  templateUrl: './enrollment-form.html',
  styleUrl: './enrollment-form.css'
})
export class EnrollmentForm {
  studentName = '';
  studentEmail = '';
  courseId: number | null = null;
  preferredSemester = '';
  agreeToTerms = false;
  submitted = false;

  onSubmit(form: NgForm): void {
    if (form.valid) {
      this.submitted = true;
      console.log('Template-Driven Form Submitted!');
      console.log('Form Value:', form.value);
      console.log('Form Valid:', form.valid);
    }
  }

  resetForm(form: NgForm): void {
    form.resetForm();
    this.submitted = false;
  }
}
