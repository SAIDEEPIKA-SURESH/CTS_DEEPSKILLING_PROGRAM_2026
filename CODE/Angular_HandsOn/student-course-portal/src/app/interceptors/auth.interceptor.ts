import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  // Clone the request and add Authorization header (Hands-On 8 step 88)
  const clonedRequest = req.clone({
    setHeaders: {
      Authorization: 'Bearer mock-token-12345'
    }
  });
  console.log('AuthInterceptor: Added Authorization Bearer header to request', req.url);
  return next(clonedRequest);
};
