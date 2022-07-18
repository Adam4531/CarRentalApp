import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

import { Car } from './car';

@Injectable({
  providedIn: 'root'
})
export class CarService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getCars(): Observable<Car[]> {
    return this.http.get<Car[]>(`${this.apiServerUrl}/cars`);
  }

  public addCars(car: Car): Observable<Car> {
    return this.http.post<Car>(`${this.apiServerUrl}/cars`, car);
  }

  public updateCars(car: Car): Observable<Car> {
    return this.http.put<Car>(`${this.apiServerUrl}/cars`, car);
  }

  public deleteCars(carId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/cars/${carId}`);
  }

}
