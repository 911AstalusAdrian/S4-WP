import { Component, OnInit } from '@angular/core';
import {UsersService} from '../services/users.service';
import {User} from '../models/user';
import {LogsService} from '../services/logs.service';
import {Log} from '../models/log';

@Component({
  selector: 'app-homepage',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  logs: Array<string[]> = [];
  allLogs: Array<string[]> = [];
  pageCount = 1;
  page = 0;
  showNext = false;
  showPrevious = false;

  constructor(private userService: UsersService, private logService: LogsService) { }

  ngOnInit(): void {
    console.log('ngOnInit called for homepage');
    // this.getAllLogs();
    this.loadTable();
    this.getAllLogs();
    // this.getPageLogs();
  }

  getAllLogs(): void {
    this.logService.getAllLogs()
      .subscribe(logsRes => this.allLogs = logsRes);
  }

  loadTable(page = 1): void {
    this.logService.getLogsPaginated(4, page)
      .subscribe(logsRes => this.logs = logsRes);
    this.page = page;
    this.showNext = false;
    this.showPrevious = false;
    if (this.pageCount > 0 && page < this.pageCount + 1) {
      this.showNext = true;
    }
    if (page > 1) {
      this.showPrevious = true;
    }
  }

  goToPreviousPage(): void {
    if (this.page > 1) {
      this.loadTable(this.page - 1);
    }
  }

  goToNextPage(): void {
    if (this.page < this.pageCount + 1) {
      this.loadTable(this.page + 1);
    }
  }

}