import { UserLogin } from './../model/UserLogin';
import { map } from 'rxjs/operators';
import { LoginResponse } from '../model/LoginResponse';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const urlLogin = 'http://localhost:8080/api/microappservice/login';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private headerAuth = new HttpHeaders({
    'Content-Type': 'application/json'
  });

  constructor(private http: HttpClient) { }

  authenticate(username, password): Observable<LoginResponse> {
    let userLogin = new UserLogin();
    userLogin.username = username;
    userLogin.password = password;
    return this.http.post<LoginResponse>
      (urlLogin, userLogin
      ).pipe(
        map(response => {
          console.log(response);
          let loginResponse = response as LoginResponse;
          return loginResponse;
        })
      );
  }

  isUserLoggedIn() {
    let username = sessionStorage.getItem('username');
    let token = sessionStorage.getItem('token');
    console.log(username);
    console.log(token);
    if (username != null && username != 'null' && token != null && token != 'null') {
      console.log('return true');
      return true;
    } else {
      console.log('return false');
      this.logOut();
      return false;
    }
  }

  logOut() {
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('username');
  }

}
