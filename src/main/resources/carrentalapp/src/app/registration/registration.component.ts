import { Component, Inject, Injectable, Injector, OnInit } from '@angular/core';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})

export class RegistrationComponent implements OnInit {

  letterNumberDotDashUnderscore: RegExp = /[a-zA-Z\d._-]+$/

  emailPattern: RegExp = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

  password1: string = "";
  password2: string = "";
  phoneNumber: string ="";
  pesel: string="";

  ngOnInit(): void {
  }

}
