import { Component, OnInit } from '@angular/core';
import { EditUser } from './edit-user';
import { EditUserService } from './edit-user.service';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { UserRequestDto } from '../user/user-request-dto';
import { UserService} from '../user/user.service';
import { MenuItem } from 'primeng/api';

@Component({
    selector: 'app-edit-user',
    templateUrl: './edit-user.component.html',
    styleUrls: ['./edit-user.component.css']
  })
  export class EditUserComponent implements OnInit {

    // firstName!: string;
    // secondName!: string;
    // login!: string;
    // password!: string;
    // email!: string;
    // phoneNumber!: string;
    // pesel!: string;
    user!: UserRequestDto;
    emailTemp: any;
    items: MenuItem[] = [];


    constructor(private editUserService: EditUserService, private router: Router) { }

    public btnClick(url: string): void {
        this.router.navigateByUrl(url);
        };

    ngOnInit(): void {
      this.emailTemp = localStorage.getItem('email')
      this.getUserByEmail();
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

      }else {
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

      public btnUpdate(): void{
        // this.updateUser(this.login, this.firstName, this.secondName, this.phoneNumber, this.email, this.password, this.pesel);
        // console.log(this.user)
        // console.log(this.user.login)
        this.editUserService.putUser(this.user)
       }


      public getUserByEmail(){
         this.emailTemp = localStorage.getItem('email');

         this.editUserService.getUserByEmail().subscribe((response: any) => {
          this.user = response;
          console.log(response)
          console.log(this.user)
         })
      }

    public isLogged() {
      return sessionStorage.length > 0;
    }


  }
