import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EditUser } from './edit-user';
import { environment } from 'src/environments/environment';

@Injectable({
    providedIn: 'root'
})
export class EditUserService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

 public putUser(user: EditUser): Observable<EditUser> {
    return this.http.put<EditUser>(`${this.apiServerUrl}/edit`, user);
  }
}