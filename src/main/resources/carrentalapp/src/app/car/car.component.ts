import { Component, OnInit } from '@angular/core';
import { Car } from './car';
import { CarService } from './car.service';
import { PrimeNGConfig } from 'primeng/api';

@Component({
  selector: 'app-car',
  templateUrl: './car.component.html',
  styleUrls: ['./car.component.css']
})
export class CarComponent implements OnInit {

  public cars: Car[] = [];
  public columns: any[] = [];

  constructor(private carService: CarService, private primengConfig: PrimeNGConfig){
  }

  ngOnInit(): void{

    this.primengConfig.ripple = true;

    this.columns = [
      { field: 'brand', header: 'Brand' },
      { field: 'model', header: 'Model' },
      { field: 'typeOfFuel', header: 'Type of Fuel' },
      { field: 'engineCapacity', header: 'Engine Capacity' },
      { field: 'productionYear', header: 'Production Year' },
      { field: 'cost', header: 'Cost' }
    ];
    this.getCars();
  }

  klik(): void{
    console.log(this.cars);

  }

  public getCars(): void {

    this.carService.getCars().subscribe((response: any) => {
      this.cars = response;
      console.log(response);
      // this.cars.push(<CarEntity>{carName:"name561231321", id:212313227});
      // this.cars.push(response[0]);
    });

    }
  //     (response: CarEntity[]) => {
  //       this.cars = response;
  //     },
  //     (error: HttpErrorResponse) => {
  //       alert(error.message);
  //     }

  //   );
  // }
  title = 'carrentalapp';
}
