import { Component, OnInit } from '@angular/core';
import { PrimeNGConfig } from 'primeng/api';
import { ReservationDto } from './reservations';
import { ReservationsService } from './reservations.service';
import { UserService } from '../user/user.service';
import { UserRequestDto } from '../user/user-request-dto';
import { Router } from '@angular/router';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css']
})
export class ReservationsComponent implements OnInit {

  public reservations: ReservationDto[] = [];
  public columns: any[] = [];

  emailTemp: any;

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
      { field: 'brand', header: 'Brand'},
      { field: 'model', header: 'Model'},
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

  public getReservations(): void {
    this.reservationsService.getAllReservationsByUserEmail(this.emailTemp).subscribe((response: any) => {
      this.reservations = response;
    });
  }

}
