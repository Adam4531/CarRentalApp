import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserRequestDto } from 'C:/PRAKTYKA/SPRING/zetosoftware/src/main/resources/carrentalapp/src/app/user/user-request-dto';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public register(user: UserRequestDto): Observable<Object> {
    return this.http.post<UserRequestDto>(`${this.apiServerUrl}/api/registration`, user);
  }
}
