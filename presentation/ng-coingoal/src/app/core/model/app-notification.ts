import {NotificationType} from "./notification.enum";

export interface AppNotification {
  text: string
  delay?: number
  type?: NotificationType
}
