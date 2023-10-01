import {HttpClientModule} from "@angular/common/http";
import {NgModule} from "@angular/core";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {BrowserModule} from "@angular/platform-browser";
import {NgSelectModule} from "@ng-select/ng-select";
import {GoalRowComponent} from './components/goal-row/goal-row.component';
import {GoalListComponent} from './components/goal-list/goal-list.component';
import {PositionRowComponent} from './components/position-row/position-row.component';
import {PositionPageComponent} from "./pages/position-page.component";
import {PositionRoutingModule} from "./position-routing.module";

@NgModule({
  declarations: [
    PositionPageComponent,
    PositionRowComponent,
    GoalRowComponent,
    GoalListComponent
  ],
  imports: [
    BrowserModule,
    PositionRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    NgSelectModule,
    FormsModule
  ],
  providers: [],
})
export class PositionModule {

}
