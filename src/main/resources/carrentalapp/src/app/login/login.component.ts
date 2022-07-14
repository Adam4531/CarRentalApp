import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})


export class LoginComponent implements OnInit {

  model: any = {};

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private http: HttpClient
    ) { }

  ngOnInit(): void {
    // sessionStorage.setItem('token', '');
  }

//   login() {
//     let url = 'http://localhost:8080/users/login';
//     let result = this.http.post(url, {
//         userName: this.model.username,
//         password: this.model.password
//     }).pipe(map(res => res)).subscribe(isValid => {
//         if (isValid) {
//             sessionStorage.setItem(
//               'token',
//               btoa(this.model.username + ':' + this.model.password)
//             );
//             this.router.navigate(['']);
//         } else {
//             alert("Authentication failed.");
//         }
//     });
// }
}
