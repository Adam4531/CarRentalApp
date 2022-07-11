import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { CarComponent } from './car/car.component';
import { ReservationsComponent } from './reservations/reservations.component';
import { LoginComponent } from './login/login/login.component';


const routes: Routes = [
  { path: 'cars', component: CarComponent },
  { path: 'reservations', component: ReservationsComponent },
  { path: 'login', component: LoginComponent }
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]  // , CommonModule add ?!
})
export class AppRoutingModule { }
