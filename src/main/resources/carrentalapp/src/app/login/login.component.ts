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

  // btnLogin(): void {
  //   console.log("PRZYCISK PRZYCISK PRZYCISK PRZYCISK PRZYCISK");
  //   this.login();
  //   // this.router.navigateByUrl('/home');
  // }

//   login() {
//     let url = '/api/login';
//     this.http.post<any>(url, {
//       email: this.model.email,
//       password: this.model.password
//     }).subscribe(response => {
//       console.log(response)
//       console.log("BLALBALBLABA")
//       if (response) {
//         console.log("FUNKCJA ........");
//         this.sessionId = response.sessionId;
          
//         sessionStorage.setItem(
//           'token',
//           this.sessionId
//         );
//         this.router.navigate(['']);
//         alert("SUCCESSFULLY")
//       } 
    
//       else {
//           alert("Authentication failed.")
//       }
//     });
// }
  
}
