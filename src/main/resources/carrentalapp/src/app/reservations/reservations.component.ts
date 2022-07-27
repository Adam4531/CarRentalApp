import { Component, OnInit } from '@angular/core';
import { PrimeNGConfig } from 'primeng/api';
import { ReservationDto } from './reservations';
import { ReservationsService } from './reservations.service';
import { UserService } from '../user/user.service';
import { UserRequestDto } from '../user/user-request-dto';
import { Router } from '@angular/router';
import { ReservationDate } from './date';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css']
})
export class ReservationsComponent implements OnInit {

  public reservations: ReservationDto[] = [];
  public columns: any[] = [];
  displayBasic: boolean = false;
  emailTemp: any;
  public from!: any;
  public to!: any;
  public price!: number;
  public date2: ReservationDate = new ReservationDate();

  constructor(
    private reservationsService: ReservationsService,
    private primengConfig: PrimeNGConfig,
    private router: Router) {
  }

  ngOnInit(): void {

    console.log(localStorage.getItem('email'))
    this.primengConfig.ripple = true;

    this.columns = [
      // { field: 'id', header: 'ID'},
      { field: 'carId', header: 'Car'},
      { field: 'dateStart', header: 'Start of reservation'},
      { field: 'dateEnd', header: 'End of reservation'},
      { field: 'cost', header: 'Total Cost'},
      { field: 'paymentInAdvance', header: 'Payment in Advance'}
  ];
    this.getReservations();
  }

  public btnClick(x: string) {
    this.router.navigateByUrl(x);
  }

  public btnReservations(){
    this.emailTemp = localStorage.getItem('email');
    this.getReservations
  }

  public btnDelete(id: number){
    this.deleteReservationById(id);
    window.location.reload();
    // this.displayBasic = false;
    // this.router.navigateByUrl('/reservations');
  }

  public btnEdit(id: number){
    this.changeReservationDatesByReservationId(id);
    this.displayBasic = false;
    window.location.reload();
  }

  public getReservations(): void {
    this.reservationsService.getAllReservationsByUserEmail(this.emailTemp).subscribe((response: any) => {
      this.reservations = response;
      console.log(response);
      console.log(localStorage.getItem('email'))
    });
  }

  public deleteReservationById(id: number): void {
    console.log("DELETING RESERVATION WITH ID ");
    console.log(id);
    this.reservationsService.deleteReservationById(id).subscribe((response: any) => {
      console.log(response);
    });
  }

  public changeReservationDatesByReservationId(id: number): void {
    this.date2.dateStart = this.from;
    console.log(id);
    console.log(this.from);
    this.date2.dateEnd = this.to;
    console.log(this.to);
    this.reservationsService.changeReservationDatesByReservationId(id, this.date2).subscribe((response: any) => {
      console.log(response);
    });
  }

  showBasicDialog() {
    this.displayBasic = true;
  }

  // public rentCost(): void {
  //   const fromDate = new Date(this.from);
  //   const toDate = new Date(this.to);
  //   var diff = toDate.getTime() - fromDate.getTime();
  //   if (diff > 0) {
  //     diff = Math.ceil(diff / (1000 * 3600 * 24));
  //     this.price = diff * this.selectedCar.pricePerDayRent;
  //     this.price = Number(this.price.toFixed(2));
  //   }
  //     else this.price = 0;
  // }
}
