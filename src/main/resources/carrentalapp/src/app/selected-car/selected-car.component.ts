import { Component, OnInit } from '@angular/core';
import { SelectedCar } from './selected-car';
import { ReservationForCar } from './reservation-for-car';
import { SelectedCarService } from './selected-car.service';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';

@Component({
  selector: 'app-selected-car',
  templateUrl: './selected-car.component.html',
  styleUrls: ['./selected-car.component.css']
})
export class SelectedCarComponent implements OnInit {

  public reservations: ReservationForCar[] = [];
  public columns: any[] = [];
  selectedCar!: SelectedCar;
  id!: number;

  from!: string;
  to!: string;
  // DODAN0 TYLKO NA POTRZEBY ZROBIENIA WIDOKU - DO ZMIANY

  constructor(private selectedCarService: SelectedCarService, private _Activatedroute:ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.id = this._Activatedroute.snapshot.params['id'];
    this.getCar();

    this.columns = [
      { field: 'userId', header: 'UserId' },
      { field: 'carId', header: 'CarId' },
      { field: 'dateStart', header: 'Date of Start' },
      { field: 'dateEnd', header: 'Date of End' },
      { field: 'cost', header: 'Cost' },
      { field: 'paymentInAdvance', header: 'Payment in Advance' },
    ];
    this.getReservationsForCar();
  }

  public getCar(): void {

    this.selectedCarService.getCar(this.id).subscribe((response: any) => {
      this.selectedCar = response;
    });
  }

  public getReservationsForCar(): void {

    this.selectedCarService.getReservationsForCar(this.id).subscribe((response: any) => {
      this.reservations = response;
    });
  }

  public btnClick(url: string): void {
    this.router.navigateByUrl(url);
    };

}
