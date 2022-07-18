import { Component, OnInit } from '@angular/core';
import { SelectedCar } from './selected-car';
import { SelectedCarService } from './selected-car.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-selected-car',
  templateUrl: './selected-car.component.html',
  styleUrls: ['./selected-car.component.css']
})
export class SelectedCarComponent implements OnInit {

  selectedCar!: SelectedCar;
  id!: number;

  constructor(private selectedCarService: SelectedCarService, private _Activatedroute:ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this._Activatedroute.snapshot.params['id'];
    this.getCar();
  }

  public getCar(): void {

    this.selectedCarService.getCar(this.id).subscribe((response: any) => {
      this.selectedCar = response;
    });
  }
}