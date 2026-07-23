import { inject } from '@angular/core';
import { HttpInterceptorFn } from '@angular/common/http';
import { LoadingService } from '../services/loading.service';
import { finalize } from 'rxjs/operators';

export const loadingInterceptor: HttpInterceptorFn = (req, next) => {
  const loadingService = inject(LoadingService);
  
  // Show spinner before routing/sending request (Hands-On 8 step 91)
  loadingService.show();

  return next(req).pipe(
    // Finalize runs on complete or error, hiding the spinner (equivalent to finally block)
    finalize(() => {
      loadingService.hide();
    })
  );
};
