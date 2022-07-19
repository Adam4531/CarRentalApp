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
      // this.router.navigate(['localhost:4200/']);
    }).subscribe(
      
      res => {
        console.log(res);
      
      if (res) {
        // this.router.navigate(['localhost:4200/']);
        this.sessionId = res.sessionId;
        console.log(res.sessionId);
        console.log(res);
        console.log(this.sessionId);

        sessionStorage.setItem(
          'token',
          this.sessionId
        );
        this.router.navigate(['localhost:4200/']);
      } else {
          alert("Authentication failed.")
      }
    });
  }
//ewq
}
