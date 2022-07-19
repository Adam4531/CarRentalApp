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
    // id!: number;

    constructor(private editUserService: EditUserService, private router: Router) { }

    public btnClick(url: string): void {
        this.router.navigateByUrl(url);
        };

    ngOnInit(): void {
        // this.id = this._Activatedroute.snapshot.params['id'];
        // this.getUser();
      }


  }