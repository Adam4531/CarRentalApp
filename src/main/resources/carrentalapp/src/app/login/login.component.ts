import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})


export class LoginComponent implements OnInit {

  constructor(private router: Router) { 
    
  }

  password!: string;
  email!: string;
  // DODAN0 TYLKO NA POTRZEBY ZROBIENIA WIDOKU - DO ZMIANY

  ngOnInit(): void {
  }

  public btnClick(url: string): void {
    this.router.navigateByUrl(url);
    };

}
