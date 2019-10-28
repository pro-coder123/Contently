import { Component, OnInit } from '@angular/core';
import {MatCardModule} from '@angular/material/card';
import {MatTableModule} from '@angular/material/table';



export interface PeriodicElement {
  Title: string;
  position: number;
  Views: number;
  Rating: string;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {position: 1, Title: 'Book 1', Views: 10079, Rating: '1/4'},
  {position: 2, Title: 'Book 2', Views: 4026, Rating: '2/4'},
  {position: 3, Title: 'Book 3', Views: 6941, Rating: '3/4'},
  {position: 4, Title: 'Book 4', Views: 90122, Rating: '4/4'},
  {position: 5, Title: 'Book 5', Views: 10811, Rating: '4/4'},
  {position: 6, Title: 'Book 6', Views: 120107, Rating: '3/4'},
  {position: 7, Title: 'Book 7', Views: 140067, Rating: '2/4'},
  {position: 8, Title: 'Book 8', Views: 159994, Rating: '1/4'},
  {position: 9, Title: 'Book 9', Views: 189984, Rating: '2/4'},
  {position: 10, Title: 'Book 10', Views: 201797, Rating: '3/4'},
];

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  displayedColumns: string[] = ['position', 'Title', 'Views', 'Rating'];
  dataSource = ELEMENT_DATA;
  constructor() { }

  ngOnInit() {
  }

}
