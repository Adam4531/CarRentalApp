import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private router: Router, private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get<any>('/api/logged').subscribe(res => {
      if (res) {
        console.log('YOU ARE LOGGED IN !! ', res);
      } else {
          alert("Failed !!!!!!!!!!")
      }
    });
  }
  

}
