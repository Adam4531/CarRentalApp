import { Component, OnInit } from '@angular/core';
import { PrimeNGConfig } from 'primeng/api';
import { ReservationDto } from './reservations';
import { ReservationsService } from './reservations.service';
import { UserService } from '../user/user.service';
import { UserRequestDto } from '../user/user-request-dto';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css']
})
export class ReservationsComponent implements OnInit {

  public reservations: ReservationDto[] = [];
  public columns: any[] = [];

  private email!: string

  user!: UserRequestDto

  constructor(private reservationsService: ReservationsService, private userService: UserService, private primengConfig: PrimeNGConfig) { }

  ngOnInit(): void {

    this.primengConfig.ripple = true;

    // this.email = this.user.email

    this.columns = [
      { field: 'id', header: 'ID'},
      { field: 'carId', header: 'car'},
      { field: 'dateStart', header: 'Start of reservation'},
      { field: 'dateEnd', header: 'End of reservation'},
      { field: 'cost', header: 'Total Cost'},
      { field: 'paymentInAdvance', header: 'Payment in Advance'}
  ];

    this.getReservations();
  }

  public getReservations(): void {
    this.reservationsService.getAllReservationsByUserEmail(this.email).subscribe((response: any) => {
      this.reservations = response;
      console.log(response);
    });

  }
  

}
