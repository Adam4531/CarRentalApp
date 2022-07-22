import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Input, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { ButtonModule } from 'primeng/button';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { InputTextModule } from 'primeng/inputtext';
import { TableModule } from 'primeng/table';
import { AppRoutingModule } from './app-routing.module';
import { CarComponent } from './cars/car.component';
import { LoginComponent } from './login/login.component';
import { UserComponent } from './user/user.component';
import { InputNumberModule } from 'primeng/inputnumber';
import { PasswordModule } from 'primeng/password';
import { FormsModule } from '@angular/forms';
import { RegistrationComponent } from './registration/registration.component';
import { DividerModule } from 'primeng/divider';
import { KeyFilterModule } from 'primeng/keyfilter';
import { InputMaskModule } from 'primeng/inputmask';
import { HomeComponent } from './home/home.component';
import { RequestInterceptor } from './request.interceptor';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { EditUserComponent } from './edit-user/edit-user.component';
import { FilterComponent } from './filter/filter.component';
import { SelectedCarComponent } from './selected-car/selected-car.component';
import { ReservationSummaryComponent } from './reservation-summary/reservation-summary.component';
import { ReservationsComponent } from './reservations/reservations.component';
import {MenubarModule} from 'primeng/menubar';
import {MenuItem} from 'primeng/api';
import { FaqComponent } from './faq/faq.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegistrationComponent,
    CarComponent,
    UserComponent,
    FilterComponent,
    SelectedCarComponent,
    ReservationSummaryComponent,
    EditUserComponent,
    ReservationsComponent,
    FaqComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    DividerModule,
    BrowserAnimationsModule,
    InputTextModule,
    TableModule,
    PasswordModule,
    FormsModule,
    DividerModule,
    KeyFilterModule,
    InputMaskModule,
    AppRoutingModule,
    InputNumberModule,
    PasswordModule,
    FormsModule,
    KeyFilterModule,
    ToastModule,
    ButtonModule,
    MenubarModule
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: RequestInterceptor, multi: true }, MessageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
