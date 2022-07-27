import { Component, OnInit } from '@angular/core';
import { EditUser } from './edit-user';
import { EditUserService } from './edit-user.service';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';

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

    constructor(private editUserService: EditUserService, private router: Router) { }

    ngOnInit(): void {

    }

    public btnClick(url: string): void {
      this.router.navigateByUrl(url);
      };

  }
