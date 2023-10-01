import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {PositionPageComponent} from "./pages/position-page.component";

const routes: Routes = [
  {
    path: 'positions',
    component: PositionPageComponent,
    children: []
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PositionRoutingModule {

}
