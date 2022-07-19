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

  constructor(private router: Router, private http: HttpClient) {}

  ngOnInit(): void {
    this.http.get<any>('/').subscribe(res => {
      if (res) {
        console.log('YOU ARE LOGGED IN !! ', res);
      } else {
          alert("Failed !!!!!!!!!!")
      }
    });
  }

  public btnClick(url: string): void {
    this.router.navigateByUrl(url);
    };
}
