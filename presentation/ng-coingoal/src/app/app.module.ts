import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {RouterModule} from "@angular/router";
import {AppRoutingModule} from "./app-routing.module";

import {AppComponent} from './app.component';
import {CoreModule} from "./core/core.module";
import {HomePageComponent} from './home/home-page.component';
import {PositionModule} from "./position/position.module";

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent
  ],
  imports: [
    BrowserModule,
    CoreModule,
    PositionModule,
    AppRoutingModule,
    RouterModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
