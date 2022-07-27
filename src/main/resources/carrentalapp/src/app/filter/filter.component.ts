import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CarFilterDto } from '../cars/car-filter-dto';
import { BodyType } from '../cars/enums/body-type';
import { TypeOfFuel } from '../cars/enums/type-of-fuel';


@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.css']
})
export class FilterComponent implements OnInit {

  carFilterDto : CarFilterDto = new CarFilterDto();
  bodyTypes : BodyType[];
  fuelTypes : TypeOfFuel[];

  constructor(private router: Router) {
    this.bodyTypes = [
      BodyType.SUV, BodyType.STATION_WAGON, BodyType.SEDAN, BodyType.HATCHBACK, BodyType.COUPE, BodyType.CABRIOLET
    ];
    this.fuelTypes = [
      TypeOfFuel.LPG, TypeOfFuel.ELECTRIC, TypeOfFuel.DIESEL, TypeOfFuel.BENZINE
    ]
  }

  ngOnInit(): void {
  }

  public btnClick(url: string): void {
    this.router.navigateByUrl(url);
    };


}
