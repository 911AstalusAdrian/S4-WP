import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {Log} from '../models/log';
import {Observable, of} from 'rxjs';
import {catchError} from 'rxjs/operators';

class Page {
  // tslint:disable-next-line:variable-name
  page_count = 0;
  // tslint:disable-next-line:variable-name
  page_content: Array<string[]> = [];
}

@Injectable({
  providedIn: 'root'
})
export class LogsService {

  private getAllLogsURL = 'WP-Lab8/getAllLogs.php';
  private getLogsPaginatedURL = 'WP-Lab8/getAllLogsPaginated.php';
  private getLogsPaginatedForUserURL = 'WP-Lab8/getAllLogsPaginatedForUser.php'
  private updateLogURL = 'WP-Lab8/updateLog.php';
  private addLogURL = 'WP-Lab8/addLog.php';
  private deleteLogURL = 'WP-Lab8/deleteLog.php';
  private getFilteredLogsURL = 'WP-Lab8/getLogsByTypeAndSeverity.php';
  userId = '1';
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };
  constructor(private http: HttpClient) { }

  setUserId(id: string): void {
    this.userId = id;
  }

  getAllLogs(): Observable<Array<string[]>> {
    /* body of the method */
    // @ts-ignore
    return this.http.get<Array<string[]>>(this.getAllLogsURL);
  }

  getLogsPaginated(pageSize: number, page: number): Observable<Array<string[]>> {
    return this.http.get<Array<string[]>>(this.getLogsPaginatedURL +
      `?pageSize=${pageSize}&page=${page}`);
  }

  getLogsPaginatedForUser(pageSize: number, page: number): Observable<Array<string[]>> {
    return this.http.get<Array<string[]>>(this.getLogsPaginatedForUserURL +
      `?pageSize=${pageSize}&page=${page}` + `&userId=user${sessionStorage.getItem('id')}`);
  }

  filterLogs(type: string, severity: string): Observable<Array<string[]>> {
    return this.http.get<Array<string[]>>(this.getFilteredLogsURL + `?type=${type}` + `&severity=${severity}` +
      `&userId=user${sessionStorage.getItem('id')}`);
  }

  addLog(type: string, severity: string, date: string, message: string): Observable<string> {
    return this.http.get(this.addLogURL + `?type=${type}` + `&severity=${severity}` +
      `&date=${date}` + `&userId=user` + `${sessionStorage.getItem('id')}` + `&message=${message}`,
      {responseType: 'text'})
      .pipe(catchError(this.handleError<string>('addLog', '')));
  }

  updateLog(logId: string, type: string, severity: string, date: string, message: string): Observable<string> {
    return this.http.get(this.updateLogURL + `?logId=${logId}` + `&type=${type}` + `&severity=${severity}` +
      `&date=${date}` + `&userId=user${sessionStorage.getItem('id')}` + `&message=${message}`,
      {responseType: 'text'})
      .pipe(catchError(this.handleError<string>('updateLog', '')));
  }

  deleteLog(logId: string): Observable<string> {
    return this.http.get(this.deleteLogURL + `?logId=${logId}` + `&userId=user${sessionStorage.getItem('id')}`,
      {responseType: 'text'})
      .pipe(catchError(this.handleError<string>('deleteLog', '')));
  }


  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // // TODO: better job of transforming error for user consumption
      // this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}