import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-faq',
  templateUrl: './faq.component.html',
  styleUrls: ['./faq.component.css']
})
export class FaqComponent implements OnInit {

  constructor(private router: Router) { }

  public emailTemp: any;
  items: MenuItem[] = [];

  ngOnInit(): void {
    if(this.isLogged()) {

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
          routerLink: '/login'
        },
        {
          label: 'My account',
          routerLink: '/login'
        },
        {
          label: 'FAQ',
          routerLink: '/help'
        }
    ];
  }
}

  public btnClick(url: string): void {
    this.router.navigateByUrl(url);
  }

  public isLogged() {
    return sessionStorage.length > 0;
  }

}
