import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
<<<<<<< HEAD
import { CarService } from './car.service';
import { ReservationsComponent } from './reservations/reservations.component';
=======
import { CarService } from './car/car.service';
import {ButtonModule} from 'primeng/button';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {InputTextModule} from 'primeng/inputtext';
import { LoginComponent } from './login/login/login.component';
import {TableModule} from 'primeng/table';
>>>>>>> d68663dbdc259142351fc67b3ba98d3aa1e0339d

@NgModule({
  declarations: [
    AppComponent,
<<<<<<< HEAD
    ReservationsComponent
=======
    LoginComponent
>>>>>>> d68663dbdc259142351fc67b3ba98d3aa1e0339d
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ButtonModule,
    BrowserAnimationsModule,
    InputTextModule,
    TableModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
