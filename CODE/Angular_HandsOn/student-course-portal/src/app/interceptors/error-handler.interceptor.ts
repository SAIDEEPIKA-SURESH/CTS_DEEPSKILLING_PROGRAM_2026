import { inject } from '@angular/core';
import { HttpInterceptorFn } from '@angular/common/http';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

export const errorHandlerInterceptor: HttpInterceptorFn = (req, next) => {
  const router = inject(Router);

  return next(req).pipe(
    catchError(error => {
      console.error('Global HTTP Error Intercepted:', error);
      
      // Global error handling rules (Hands-On 8 step 90)
      if (error.status === 401) {
        console.warn('Unauthorized request. Redirecting to home/login...');
        router.navigate(['/']);
      } else if (error.status === 500) {
        console.error('Internal Server Error (500).');
        alert('Global HTTP Error: A server-side error occurred (500). Please try again later.');
      }
      
      return throwError(() => error);
    })
  );
};
