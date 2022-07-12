import { Component, OnInit } from '@angular/core';
import { Car } from './car/car';
import { CarService } from './car/car.service';
import { PrimeNGConfig } from 'primeng/api';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  public cars: Car[] = [];
  public columns: any[] = [];

  constructor(private carService: CarService, private primengConfig: PrimeNGConfig, private router: Router){
  }

  ngOnInit(): void{
    this.primengConfig.ripple = true;
  }

  public getCars(): void {

    this.carService.getCars().subscribe((response: any) => {
      this.cars = response;
      console.log(response);
    });
    }

  public btnClick(url: string): void {
    this.router.navigateByUrl(url);
    };

  title = 'carrentalapp';
}


// myComponent.component.thml
