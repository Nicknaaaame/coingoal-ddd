import {CommonModule} from "@angular/common";
import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {NgbToastModule} from "@ng-bootstrap/ng-bootstrap";
import {NotificationsComponent} from "./components/notifications/notifications.component";

@NgModule({
  declarations: [
    NotificationsComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    NgbToastModule
  ],
  exports: [
    NotificationsComponent
  ]
})
export class CoreModule {
}
