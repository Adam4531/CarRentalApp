import { Component, OnInit } from '@angular/core';
import { EditUser } from './edit-user';
import { EditUserService } from './edit-user.service';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { UserRequestDto } from '../user/user-request-dto';
import { UserService} from '../user/user.service';
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
      private editUserService: EditUserService, 
      private router: Router
      ) { 
    }

    public btnClick(url: string): void {
        this.router.navigateByUrl(url);
        };

    ngOnInit(): void {

         this.getUserByEmail();
         
      }

    public btnUpdate() {
        console.log(this.user)
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

  }