import { map } from 'rxjs/operators';
import { UserGetResponse } from './../../model/UserGetResponse';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const urlEndPoint = "http://localhost:8080/api/microappservice/user";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private headerAuth = new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': 'Bearer eyJhbGciOiJIUzM4NCJ9.eyJhdXRob3JpdGllcyI6Ilt7XCJhdXRob3JpdHlcIjpcIlJPTEVfQURNSU5cIn1dIiwic3ViIjoiUk9PVCIsImlhdCI6MTU3MjM3Mjc4NiwiZXhwIjoxNTcyNDE1OTg2fQ.SUQbgv0NrjmUQmCkzARSyDsgLlv9_nkHiCt3ILVuxSoz18JPnwSJ3vIBUwASWeAS'
  });

  constructor(private http: HttpClient) { }

  getUsers(): Observable<UserGetResponse> {
    return this.http.get<UserGetResponse>
      (urlEndPoint,
        {
          headers: this.headerAuth,
          responseType: 'json',
          withCredentials: true
        }
      ).pipe(
        map(response => {
          let userGetResponse = response as UserGetResponse;
          userGetResponse.listUsers = userGetResponse.listUsers.map(user => {
            user.statusBool = user.status == 'ENABLE'? true: false;
            return user;
          });
          return userGetResponse;
        })
      );
  }

}
