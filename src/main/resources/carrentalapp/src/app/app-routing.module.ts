import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { CarComponent } from './cars/car.component';
import { ReservationsComponent } from './reservations/reservations.component';
import { LoginComponent } from './login/login.component';
import { UserComponent } from './user/user.component';
import { RegistrationComponent } from './registration/registration.component';
import { HomeComponent } from './home/home.component';
import { FilterComponent } from './filter/filter.component';
import { SelectedCarComponent } from './selected-car/selected-car.component';

const routes: Routes = [
  { path: 'cars', component: CarComponent },
  { path: 'reservations', component: ReservationsComponent },
  { path: 'login', component: LoginComponent },
  { path: 'users', component: UserComponent},
  { path: 'registration', component: RegistrationComponent},
  { path: 'home', component: HomeComponent},
  { path: 'filter', component: FilterComponent},
  { path: 'selected-car/:id', component: SelectedCarComponent},
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]  // , CommonModule add ?!
})
export class AppRoutingModule { }
