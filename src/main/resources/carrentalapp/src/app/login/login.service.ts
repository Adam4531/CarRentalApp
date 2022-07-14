import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { map } from 'rxjs/operators';
import { Component, OnInit } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private anything: any

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public login() {
    return this.http.post(`${this.apiServerUrl}/login`, this.anything);
  }

//   login() {
//     let url = 'http://localhost:8080/login';
//     this.http.post<Observable<boolean>>(url, {
//       userName: this.model.username,
//       password: this.model.password
//   }).subscribe(isValid => {
//       if (isValid) {
//           sessionStorage.setItem(
//             'token', 
//             btoa(this.model.username + ':' + this.model.password)
//           );
//     this.router.navigate(['']);
//       } else {
//           alert("Authentication failed.")
//       }
//   });
// }


}
