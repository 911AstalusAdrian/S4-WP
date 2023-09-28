import { Component, OnInit } from '@angular/core';
import {UsersService} from '../services/users.service';
import {LogsService} from '../services/logs.service';

@Component({
  selector: 'app-filter-page',
  templateUrl: './filter-page.component.html',
  styleUrls: ['./filter-page.component.css']
})
export class FilterPageComponent implements OnInit {

  logs: Array<string[]> | undefined;

  constructor(private userService: UsersService, private logService: LogsService) { }

  ngOnInit(): void {
    console.log('ngOnInit called for filterPage');
    // this.getAllLogs();
  }

  // getAllLogs(): void {
  //   this.logService.getAllLogs().subscribe(logs => this.logs = logs);
  // }

  loadTable(type: string, severity: string): void {
    this.logService.filterLogs(type, severity)
      .subscribe(filteredLogs => this.logs = filteredLogs);
}

}