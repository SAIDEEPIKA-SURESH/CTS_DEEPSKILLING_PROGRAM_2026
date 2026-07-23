import { Component } from '@angular/core';
import { NgIf, NgFor } from '@angular/common';
import { NotificationService } from '../../services/notification.service';

@Component({
  selector: 'app-notification',
  imports: [NgIf, NgFor],
  templateUrl: './notification.html',
  styleUrl: './notification.css',
  providers: [NotificationService] // Component-level provider
})
export class Notification {
  messageInput = '';

  constructor(private notificationService: NotificationService) {}

  get notifications(): string[] {
    return this.notificationService.getNotifications();
  }

  addNotification(message: string): void {
    if (message.trim()) {
      this.notificationService.addNotification(message.trim());
    }
  }

  clear(): void {
    this.notificationService.clearNotifications();
  }

  /*
   * WHY COMPONENT-LEVEL PROVIDING CREATES A SCOPED INSTANCE:
   *
   * When we add a service to the 'providers' array of a @Component decorator (instead of providedIn: 'root'),
   * Angular's Dependency Injection (DI) system creates a new, dedicated instance of this service 
   * for this component and its child components. 
   *
   * Every time this component is instantiated, a separate service instance is created.
   * If there are multiple app-notification elements on the same page, each will have its own 
   * isolated NotificationService instance with its own state. 
   * This is useful for state encapsulation (e.g., when a service holds local UI state specific to a single component subtree).
   */
}
export { NotificationService };
