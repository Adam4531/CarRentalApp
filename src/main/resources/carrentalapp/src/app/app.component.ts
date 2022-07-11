import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Car } from './car';
import { CarService } from './car.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  public cars: Car[] = [];

  constructor(private carService: CarService){
  }

  ngOnInit(): void{
    // console.log("bla");
    // this.cars.push(<CarEntity>{car_name:"name56", id:227})
    this.getCars();
  }

  klik(): void{
    // this.getCars();
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
