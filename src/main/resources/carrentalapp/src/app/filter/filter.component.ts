import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.css']
})
export class FilterComponent implements OnInit {

  brand!: string;
  model!: string;
  engineCapacity!: string;
  bodyType!: string;
  typeOfFuel!: string;
  productionYear!: string;
  from!: string;
  to!: string;
  // DODAN0 TYLKO NA POTRZEBY ZROBIENIA WIDOKU - DO ZMIANY

  constructor(private router: Router) { 

  }

  ngOnInit(): void {
  }

  public btnClick(url: string): void {
    this.router.navigateByUrl(url);
    };

}
