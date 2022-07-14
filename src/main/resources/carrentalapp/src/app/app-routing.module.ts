import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { CarComponent } from './car/car.component';
import { ReservationsComponent } from './reservations/reservations.component';
import { LoginComponent } from './login/login.component';
import { UserComponent } from './user/user.component';

const routes: Routes = [
  { path: 'cars', component: CarComponent },
  { path: 'reservations', component: ReservationsComponent },
  { path: 'login', component: LoginComponent },
  { path: 'login.html', component: LoginComponent },
  { path: 'users', component: UserComponent}
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule] 
})
export class AppRoutingModule { }
