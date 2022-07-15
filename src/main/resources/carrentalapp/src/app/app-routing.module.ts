import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { CarComponent } from './car/car.component';
import { ReservationsComponent } from './reservations/reservations.component';
import { LoginComponent } from './login/login.component';
import { UserComponent } from './user/user.component';
import { RegistrationComponent } from './registration/registration.component';

const routes: Routes = [
  { path: 'cars', component: CarComponent },
  { path: 'reservations', component: ReservationsComponent },
  { path: 'login', component: LoginComponent },
  { path: 'users', component: UserComponent},
  { path: 'register', component: RegistrationComponent}
];

// localhost:4200/users
// localhost:4200/register
// localhost:4200/web/users/register


@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
