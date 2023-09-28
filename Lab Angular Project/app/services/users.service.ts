import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from '../models/user';
import {Observable, of} from 'rxjs';
import {catchError} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  private userUrl = 'http://localhost/WP-Lab8/login.php';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };
  constructor(private http: HttpClient) { }

  logIn(username: string, password: string): Observable<string> {
    return this.http.get(this.userUrl + `?username=${username}` +
      `&password=${password}`, {responseType: 'text'})
      .pipe(catchError(this.handleError<string>('logIn', '')));
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }
}