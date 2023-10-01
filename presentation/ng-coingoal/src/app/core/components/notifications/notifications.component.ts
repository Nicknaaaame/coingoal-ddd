import {Component} from '@angular/core';
import {NotificationType} from "../../model/notification.enum";
import {NotificationsService} from "../../service/notification.service";

@Component({
  selector: 'notifications',
  templateUrl: './notifications.component.html',
  styleUrls: ['./notifications.component.css']
})
export class NotificationsComponent {
  SUCCESS = NotificationType.SUCCESS
  ERROR = NotificationType.ERROR

  constructor(public readonly notificationsService: NotificationsService) {
  }
}
