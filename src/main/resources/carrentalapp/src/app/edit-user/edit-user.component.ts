import { Component, OnInit } from '@angular/core';
import { EditUser } from './edit-user';
import { EditUserService } from './edit-user.service';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';

@Component({
    selector: 'app-edit-user',
    templateUrl: './edit-user.component.html',
    styleUrls: ['./edit-user.component.css']
  })
  export class EditUserComponent implements OnInit {

    firstName!: string;
    lastName!: string;
    username!: string;
    email!: string;
    phoneNumber!: string;
    items: MenuItem[] = [];
    public emailTemp: any;

    constructor(private editUserService: EditUserService, private router: Router) { }

    public btnClick(url: string): void {
        this.router.navigateByUrl(url);
        };

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


  }
