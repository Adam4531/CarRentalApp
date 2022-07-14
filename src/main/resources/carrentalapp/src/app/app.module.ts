import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import {ButtonModule} from 'primeng/button';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {InputTextModule} from 'primeng/inputtext';
import {TableModule} from 'primeng/table';
import { AppRoutingModule } from './app-routing.module';
import { CarComponent } from './car/car.component';
import { LoginComponent } from './login/login.component';
import { UserComponent } from './user/user.component';
import { InputNumberModule } from 'primeng/inputnumber';
import { PasswordModule } from 'primeng/password';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    AppComponent,
    CarComponent,
    LoginComponent,
    UserComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ButtonModule,
    BrowserAnimationsModule,
    InputTextModule,
    TableModule,
    AppRoutingModule,
    InputNumberModule,
    PasswordModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

