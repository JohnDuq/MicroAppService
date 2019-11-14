import { ConstantsService } from './../ConstantsService';
import { UserLogin } from './../model/UserLogin';
import { map } from 'rxjs/operators';
import { LoginResponse } from '../model/LoginResponse';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private urlLogin: string;

  constructor(private http: HttpClient, private constantsService: ConstantsService) {
    this.urlLogin = this.constantsService.baseAppUrl + '/login';
  }

  public authenticate(username, password): Observable<LoginResponse> {
    let userLogin = new UserLogin();
    userLogin.username = username;
    userLogin.password = password;
    return this.http.post<LoginResponse>
      (this.urlLogin, userLogin
      ).pipe(
        map(response => {
          let loginResponse = response as LoginResponse;
          return loginResponse;
        })
      );
  }

  public isUserLoggedIn() {
    let username = sessionStorage.getItem('username');
    let token = sessionStorage.getItem('Authorization');
    if (username != null && username != 'null' && token != null && token != 'null') {
      return true;
    } else {
      this.logOut();
      return false;
    }
  }

  public logOut() {
    sessionStorage.removeItem('Authorization');
    sessionStorage.removeItem('username');
  }

}
