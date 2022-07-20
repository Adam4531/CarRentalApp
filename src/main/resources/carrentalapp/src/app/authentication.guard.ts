import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { MessageService } from 'primeng/api';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationGuard implements CanActivate {

  constructor(private router: Router, private messageService: MessageService) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {


      let token = sessionStorage.getItem('token');


      if (state.url == "/login") {
        if(token){
          this.messageService.add({life:3000, severity:'info', summary:'Login', detail:" Już jesteś zalogowany !! "})
          return this.router.parseUrl('/'); 
        }
        return true;
      }

      if(!token) {
        let url1 = "/user/edit"
        let url2 = "/user/history"
        let url3 = "/cars"
        if(state.url == url3) { //TU DODAC /user/**
          console.log("NIE JESTES ZALOGOWANY !!!!!!!!111!")
          this.messageService.add({life:3000, severity:'info', summary:'Login', detail:" Musisz się najpierw zalogować !! "})
          return this.router.parseUrl('/login');
        }
        return true;
        // console.log("NIE JESTES ZALOGOWANY !!!!!!!!111!")
        // this.messageService.add({life:10000, severity:'info', summary:'Login', detail:" Musisz się najpierw zalogować !! "})
        // return this.router.parseUrl('/login');
      }
    return true;
  }
  
}
