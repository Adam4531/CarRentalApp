import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})


export class LoginComponent implements OnInit {

  model: any = {};
  sessionId: any = "";

  constructor(
      private router: Router,
      private http: HttpClient
  ) { }

  ngOnInit(): void {
  }

  login() {
    let url = '/api/login';
    this.http.post<any>(url, {
      email: this.model.email,
      password: this.model.password
    }).subscribe(
      res => {
      // nie wywala w ogóle w konsoli response, czyli backend nie wysyła tego response'a...
      if (res) {
        this.sessionId = res.sessionId;
          
        sessionStorage.setItem(
          'token',
          this.sessionId
        );
        this.router.navigate(['']);
      } else {
          alert("Authentication failed.")
      }
    });
  }
  
}
