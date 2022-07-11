import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserDto } from './user';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})

export class UserService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getUsers(): Observable<UserDto[]> {
    return this.http.get<UserDto[]>(`${this.apiServerUrl}/users`);
  }

  public addUser(user: UserDto): Observable<UserDto> {
    return this.http.post<UserDto>(`${this.apiServerUrl}/users`, user);
  }

  public putUser(user: UserDto): Observable<UserDto> {
    return this.http.put<UserDto>(`${this.apiServerUrl}/users`, user);
  }

  public deleteUser(userId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/users/${userId}`);
  }

}
