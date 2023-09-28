import { Component, OnInit } from '@angular/core';
import {User} from '../models/user';
import {UsersService} from '../services/users.service';
import {Router} from '@angular/router';
import {LogsService} from '../services/logs.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  userId = '0';
  constructor(private userService: UsersService,
              private logService: LogsService,
              private router: Router) { }

  ngOnInit(): void {
  }

  logIn(username: string, password: string): void {
    this.userService.logIn(username, password)
      .subscribe(userId => sessionStorage.setItem('id', userId[userId.length - 1]));
    console.log('session id in login comp ' + sessionStorage.getItem('id'));
    if (sessionStorage.getItem('id') !== '100') {
      this.router.navigate(['./homepage']);
    }
  }
}