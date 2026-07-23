import { ApplicationConfig, provideZoneChangeDetection, isDevMode } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { provideStore } from '@ngrx/store';
import { provideEffects } from '@ngrx/effects';
import { provideStoreDevtools } from '@ngrx/store-devtools';

import { routes } from './app.routes';
import { courseReducer } from './store/course/course.reducer';
import { enrollmentReducer } from './store/enrollment/enrollment.reducer';
import { CourseEffects } from './store/course/course.effects';
import { authInterceptor } from './interceptors/auth.interceptor';
import { errorHandlerInterceptor } from './interceptors/error-handler.interceptor';
import { loadingInterceptor } from './interceptors/loading.interceptor';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    // Register HttpClient with interceptors (Hands-On 8 step 78 & 88)
    provideHttpClient(
      withInterceptors([
        authInterceptor, 
        errorHandlerInterceptor, 
        loadingInterceptor
      ])
    ),
    // Register NgRx Store slices (Hands-On 9 step 92 & 99)
    provideStore({
      course: courseReducer,
      enrollment: enrollmentReducer
    }),
    // Register NgRx Effects (Hands-On 9 step 97)
    provideEffects([CourseEffects]),
    // Register StoreDevtools instrument (Hands-On 9 step 92)
    provideStoreDevtools({
      maxAge: 25,
      logOnly: !isDevMode()
    })
  ]
};
