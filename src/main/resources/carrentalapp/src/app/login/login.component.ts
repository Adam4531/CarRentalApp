import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { MessageService } from 'primeng/api';



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
      private http: HttpClient,
      private messageService: MessageService
  ) { }

  ngOnInit(): void {
  }

  btnLogin() {
    this.login();
  }

  login() {
    let url = '/api/login';
    this.http.post<any>(url, {
      email: this.model.email,
      password: this.model.password
    }).subscribe(

      res => {
        console.log(res);

      if (res) {
        this.sessionId = res.sessionId;

        sessionStorage.setItem(
          'token',
          this.sessionId
        );
        this.router.navigate(['localhost:4200/']);
        this.messageService.add({life:3000, severity:'success', summary:'Login', detail:" Zostałeś pomyślnie zalogowany !! "})
      }
      // if(this.sessionId == ""){
      //   this.messageService.add({life:10000, severity:'info', summary:'Login', detail:" Już jesteś zalogowany !! "})
      //   console.log("TOST POWINIEN WYKOSCZYC !!!!!!!!!!!!!!!!!12121")
      // }
      else {
          console.log("NIE UDALO SIE ZALOGOWAC !!!")
      }
    });


  }

}
