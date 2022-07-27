import { Component, OnInit } from '@angular/core';
import { EditUserService } from './edit-user.service';
import { UserEditRequestDto } from '../user/user-edit-request-dto';

@Component({
    selector: 'app-edit-user',
    templateUrl: './edit-user.component.html',
    styleUrls: ['./edit-user.component.css']
  })
  export class EditUserComponent implements OnInit {

    user: UserEditRequestDto = new UserEditRequestDto();
    emailTemp: any;

    constructor(
      private editUserService: EditUserService
      ) {
    }

    ngOnInit(): void {
      this.emailTemp = localStorage.getItem('email')
      this.getUserByEmail();
    }

    public btnUpdate() {
        this.updateUser();
    }

    public updateUser(): void {
      this.editUserService.putUser(this.user).subscribe((response: any) => {
        console.log(this.user);

        this.user = response;
      });
    }


    public getUserByEmail(){
      this.emailTemp = localStorage.getItem('email');
      this.editUserService.getUserByEmail().subscribe((response: any) => {
        this.user = response;
      })
    }

  public isLogged() {
    return sessionStorage.length > 0;
  }
  }
