import { Component, OnInit } from '@angular/core';
import { Car } from './car/car';
import { CarService } from './car/car.service';
import { PrimeNGConfig } from 'primeng/api';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  public cars: Car[] = [];
  public columns: any[] = [];

  constructor(private carService: CarService, private primengConfig: PrimeNGConfig){
  }

  ngOnInit(): void{

    this.primengConfig.ripple = true;
  }

  klik(): void{
    console.log(this.cars);
  }

  public getCars(): void {

    this.carService.getCars().subscribe((response: any) => {
      this.cars = response;
      console.log(response);

    });

    }
  title = 'carrentalapp';
}
