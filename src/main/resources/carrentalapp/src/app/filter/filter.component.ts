import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CarFilterDto } from '../cars/car-filter-dto';

@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.css']
})
export class FilterComponent implements OnInit {

  carFilterDto : CarFilterDto = new CarFilterDto();

  constructor(private router: Router) {

  }

  ngOnInit(): void {
  }

  public btnClick(url: string): void {
    this.router.navigateByUrl(url);
    };

}
