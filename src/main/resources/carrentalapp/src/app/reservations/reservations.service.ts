import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ReservationDto } from './reservations';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ReservationsService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { 
  }

  public getAllReservationsByUserEmail(email: string): Observable<ReservationDto[]> {
    return this.http.get<ReservationDto[]>(`${this.apiServerUrl}/reservations?email=${localStorage.getItem('email')}`);
  }
}
