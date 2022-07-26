import { Component, OnInit } from '@angular/core';
import { UserRequestDto } from 'src/app/user/user-request-dto';
import { RegistrationService } from './registration.service';
import { MenuItem, Message, PrimeNGConfig } from 'primeng/api';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { ErrorsListDto } from '../errorsList/errors-list-dto';
import {MessageService} from 'primeng/api';

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

  constructor(
    private registrationService: RegistrationService,
    private primengConfig: PrimeNGConfig,
    private router: Router,
    private messageService: MessageService
    ){
  }

  items: MenuItem[] = [];
  public emailTemp: any;

  ngOnInit(): void {

    this.emailTemp = localStorage.getItem('email')
    if(this.isLogged()) {

      this.items = [
        {
          label: 'Contact Us',
            items: [
              {label: 'Warsaw', routerLink: "/warsaw",},
              {label: 'Bratislava', routerLink: "/bratislava",},
              {label: 'Berlin', routerLink: "/berlin",},
          ]
        },
        {
          label: 'Home',
          routerLink: '/'
        },
        {
          label: 'Rent a Car',
          routerLink: '/cars'
        },
        {
          label: 'My account',
          routerLink: '/edit'
        },
        {
          label: 'FAQ',
          routerLink: '/help'
        },
    ];

    }
    else {
      this.items = [
        {
          label: 'Contact Us',
            items: [
                {label: 'Warszawa', routerLink: "/warsaw",},
                {label: 'Poznań', routerLink: "/poznan",},
                {label: 'Berlin', routerLink: "/berlin",},
          ]
        },
        {
          label: 'Home',
          routerLink: '/'
        },
        {
          label: 'Rent a Car',
          routerLink: '/login'
        },
        {
          label: 'My account',
          routerLink: '/login'
        }
      ];
    }
  }

  public isLogged() {
    return sessionStorage.length > 0;
  }

  btnRegister(): void {
    console.log(this.userRequestDto);
    this.registerUser();
  }

  btnClick(x: string) {
    this.router.navigateByUrl(x);
  }

  public registerUser() {
      console.log(" REGISTRATION... " + this.userRequestDto);

      this.registrationService
      .register(this.userRequestDto)
      .subscribe( (response: any) => {
        this.errorsListDto = response;

        if( !this.errorsListDto.listOfErrorsEmpty ) {
          this.errorsListDto.errors.forEach((error) =>
          this.messageService.add({life:3000, severity:'error', summary:'Register', detail:error})
          );
        }
        else{
          this.messageService.add({life: 3000, severity:'success', summary:'Register', detail:' You have successfully signed up !'});
          this.messageService.add({life: 5500, severity:'info', summary:'Register', detail:' Now you can log in with your credentials !'});
          this.router.navigateByUrl('/login');
        }
        });
      }

}
