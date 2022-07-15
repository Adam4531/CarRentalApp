import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})

export class RegistrationComponent implements OnInit {

  firstName!: string;
  lastName!: string;
  username!: string;
  password1!: string;
  password2!: string;
  email!: string;
  phoneNumber!: string;
  pesel!: string;
  // DODAN0 TYLKO NA POTRZEBY ZROBIENIA WIDOKU - DO ZMIANY

  constructor(private router: Router){
  }

  public btnClick(url: string): void {
    this.router.navigateByUrl(url);
    };

  ngOnInit(): void {
  }

}
