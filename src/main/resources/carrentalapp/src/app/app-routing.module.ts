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
import { EditUserComponent } from './edit-user/edit-user.component';
import { RegistrationComponent } from './registration/registration.component';
import { HomeComponent } from './home/home.component';
import { AuthenticationGuard } from './authentication.guard';

const routes: Routes = [


    { path: '', canActivate:[AuthenticationGuard], children: [
    { path: '', component: HomeComponent},
    { path: 'login', component: LoginComponent },
    { path: 'cars', component: CarComponent },
    { path: 'reservations', component: ReservationsComponent },
    { path: 'users', component: UserComponent },
    { path: 'register', component: RegistrationComponent },
        { path: 'home', component: HomeComponent},
        { path: 'filter', component: FilterComponent},
        { path: 'selected-car/:id', component: SelectedCarComponent},
        { path: 'edit', component: EditUserComponent},

        { path: '**', redirectTo: '' }
  ]}
];


@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
