import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';

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
    ) {}

  ngOnInit(): void {
    this.http.get<any>('/').subscribe(res => {
      if (res) {
        console.log('YOU ARE LOGGED IN !! ', res);
      } else {
          alert("Failed GET '/' !!11!!!!!!!!!!")
      }
    });
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
      this.messageService.add({life:3000, severity:'success', summary:'Logout', detail:" Zostałeś pomyślnie wylogowany !! "})
    }
    else {
      this.messageService.add({life:3000, severity:'info', summary:'Logout', detail:" Musisz się najpierw zalogować !! "})
    }
  }

}
