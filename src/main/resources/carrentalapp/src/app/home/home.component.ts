import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import { MenuItem } from 'primeng/api';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(
    private router: Router,
    private http: HttpClient,
    private messageService: MessageService
    ) {
  }

  public emailTemp: any;
  items: MenuItem[] = [];
  displayBasic: boolean = false;
  options: any;

  overlays: any[] = [];

  ngOnInit(): void {

    this.http.get<any>('/').subscribe(res => {
      if (res) {
        console.log('YOU ARE LOGGED IN !! ', res);
      } else {
          alert("Failed GET '/' !!11!!!!!!!!!!")
      }
    });
    console.log(localStorage.getItem('email'))
    this.emailTemp = localStorage.getItem('email');

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
        },
        {
          label: 'FAQ',
          routerLink: '/help'
        },
    ];
  }

  this.options = {
    center: {lat: 53.7732837, lng: 20.4570858},
    zoom: 12
  };
}

  public btnClick(url: string): void {
    this.router.navigateByUrl(url);
  }

  public isLogged() {
    return sessionStorage.length > 0;
  }

  public btnLogout() {
    console.log("WYLOGOWYWUJESZ SIE !!! ");
    this.logout();
  }

  public logout() {
    if(sessionStorage.length > 0){
      sessionStorage.removeItem('token')
      this.router.navigateByUrl('localhost:4200/');
      this.messageService.add({life:3000, severity:'success', summary:'Logout', detail:" You have successfully logged out ! "})
    }
    else {
      this.messageService.add({life:3000, severity:'info', summary:'Logout', detail:" You have to log in first ! "})
    }
  }

  showBasicDialog() {
    this.displayBasic = true;
  }


}
