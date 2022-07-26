import { Component, OnInit } from '@angular/core';
import { Car } from './car';
import { CarService } from './car.service';
import { PrimeNGConfig } from 'primeng/api';
import { Router } from '@angular/router';

@Component({
  selector: 'app-car',
  templateUrl: './car.component.html',
  styleUrls: ['./car.component.css']
})
export class CarComponent implements OnInit {

  public cars: Car[] = [];
  public columns: any[] = [];

  constructor(
    private carService: CarService,
    private primengConfig: PrimeNGConfig,
    private router: Router
    ){
  }

  ngOnInit(): void{

    this.primengConfig.ripple = true;

    this.columns = [
      { field: 'brand', header: 'Brand' },
      { field: 'model', header: 'Model' },
      { field: 'bodyTypeEnum', header: 'Body Type' },
      { field: 'engineCapacity', header: 'Engine Capacity' },
      { field: 'productionYear', header: 'Production Year' },
      { field: 'typeOfFuelEnum', header: 'Type of Fuel' },
      { field: 'bodyTypeEnum', header: 'Body type'},
      { field: 'pricePerDayRent', header: 'Cost of rent per day' },
      { field: 'status', header: 'Status'}
    ];
    this.getCars();
  }

  public getCars(): void {
    this.carService.getCars().subscribe((response: any) => {
      this.cars = response;
    });
  }

  public btnClick(url: string) {
    this.router.navigateByUrl(url);
  }
}
