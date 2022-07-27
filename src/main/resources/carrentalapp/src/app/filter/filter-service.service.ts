import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Car } from '../cars/car';
import { CarFilterDto } from '../cars/car-filter-dto';

@Injectable({
  providedIn: 'root'
})
export class FilterService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  // public getCarsWithFilter(carFilterDto: CarFilterDto): Observable<Car[]>{
  //   return this.http.get<Car[]>(`${this.apiServerUrl}/api/filter/cars`, carFilterDto);
  // }
  
}
