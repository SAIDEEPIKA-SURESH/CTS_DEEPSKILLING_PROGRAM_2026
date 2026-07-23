import { Injectable } from '@angular/core';

@Injectable() // Omit { providedIn: 'root' } to allow component-level scoping
export class NotificationService {
  private notifications: string[] = [];

  constructor() {
    console.log('NotificationService Instance Created! Scoped locally.');
  }

  addNotification(message: string): void {
    this.notifications.push(message);
  }

  getNotifications(): string[] {
    return this.notifications;
  }

  clearNotifications(): void {
    this.notifications = [];
  }
}
