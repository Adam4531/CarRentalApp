import { Component, OnInit } from '@angular/core';
import { UserRequestDto } from 'src/app/user/user-request-dto';
import { RegistrationService } from './registration.service';
import { Message, PrimeNGConfig } from 'primeng/api';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { ErrorsListDto } from '../errorsList/errors-list-dto';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {


  letterNumberDotDashUnderscore: RegExp = /[a-zA-Z\d._-]+$/

  emailPattern: RegExp = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

  userRequestDto: UserRequestDto = new UserRequestDto();

  errorsListDto: ErrorsListDto = new ErrorsListDto();

  constructor(private registrationService: RegistrationService, private primengConfig: PrimeNGConfig, private router: Router){
  }

  ngOnInit(): void {

  }

  btnRegister(): void {
    console.log(this.userRequestDto);
    this.registerUser();

  }

  public registerUser() {
      console.log("REGISTER.............." + this.userRequestDto);

      this.registrationService
      .register(this.userRequestDto)
      .subscribe( (response: any) => {
        this.errorsListDto = response;

        if( !this.errorsListDto.listOfErrorsEmpty ) {
          this.errorsListDto.errors.forEach((error) => alert(error));
        }
        else{
          this.router.navigateByUrl('/users');
        }
        });
      }


  // public getErrorList() {
  //   this.registerUser.getErrorsList().subscribe(
  //     data => {
  //       this.projects = data;
  //     }
  //   );
  // }

  
}
