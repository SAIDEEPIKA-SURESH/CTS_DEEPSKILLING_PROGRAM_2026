import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  // Hardcoded for now (Hands-On 7 step 75)
  private loggedIn = true;

  constructor() {}

  get isLoggedIn(): boolean {
    return this.loggedIn;
  }

  login(): void {
    this.loggedIn = true;
  }

  logout(): void {
    this.loggedIn = false;
  }
}
