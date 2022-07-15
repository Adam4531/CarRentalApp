import { Component, OnInit } from '@angular/core';
import { UserRequestDto } from 'C:/PRAKTYKA/SPRING/zetosoftware/src/main/resources/carrentalapp/src/app/user/user-request-dto';
import { RegistrationService } from './registration.service';
import { PrimeNGConfig } from 'primeng/api';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {


  letterNumberDotDashUnderscore: RegExp = /[a-zA-Z\d._-]+$/

  emailPattern: RegExp = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

  userRequestDto2: UserRequestDto = new UserRequestDto();

  constructor(private registrationService: RegistrationService, private primengConfig: PrimeNGConfig, private router: Router){
  }

  ngOnInit(): void {
  }

  btnRegister(): void {
    console.log(this.userRequestDto2);
    this.registerUser();
    this.router.navigateByUrl('/users');
  }

  public registerUser() {
      console.log("REGISTER.............." + this.userRequestDto2);
      this.registrationService
      .register(this.userRequestDto2)
      .subscribe(
        data=>{alert("Succesfully registered an user !")},
        error=>("Unsuccessfully registered an user !!!! "))
      }
}
