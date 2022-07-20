import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import {ButtonModule} from 'primeng/button';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {InputTextModule} from 'primeng/inputtext';
import {TableModule} from 'primeng/table';
import {AppRoutingModule } from './app-routing.module';
import { CarComponent } from './car/car.component';
import { LoginComponent } from './login/login.component';
import { UserComponent } from './user/user.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegistrationComponent,
    CarComponent,
    UserComponent
    FilterComponent,
    SelectedCarComponent,
    EditUserComponent,
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
    ButtonModule
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: RequestInterceptor, multi: true }, MessageService],
  bootstrap: [AppComponent]
})
export class AppModule { }

