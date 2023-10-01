import {Injectable} from "@angular/core";
import {AppNotification} from "../model/app-notification";

@Injectable({providedIn: 'root'})
export class NotificationsService {
  notifications: AppNotification[] = [];

  show(notification: AppNotification): void {
    this.notifications.push(notification);
  }

  remove(notification: AppNotification): void {
    this.notifications = this.notifications.filter(t => t !== notification);
  }
}
