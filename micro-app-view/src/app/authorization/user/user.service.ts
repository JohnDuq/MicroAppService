import { map } from 'rxjs/operators';
import { UserGetResponse } from './../../model/UserGetResponse';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from 'src/app/model/User';
import { UserResponse } from 'src/app/model/UserResponse';
import { IfStmt } from '@angular/compiler';

const urlEndPoint = "http://localhost:8080/api/microappservice/user";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getUsers(): Observable<UserGetResponse> {
    return this.http.get<UserGetResponse>
      (urlEndPoint,
        {
          responseType: 'json',
          withCredentials: true
        }
      ).pipe(
        map(response => {
          let userGetResponse = response as UserGetResponse;
          userGetResponse.listUsers = userGetResponse.listUsers.map(user => {
            user.statusBool = user.status == 'ENABLE' ? true : false;
            return user;
          });
          return userGetResponse;
        })
      );
  }

  getUser(user: User): Observable<UserResponse> {
    return this.http.get<UserResponse>
      (urlEndPoint + "/" + user.idUser,
        {
          responseType: 'json',
          withCredentials: true
        }
      ).pipe(
        map(response => {
          let userResponse = response as UserResponse;
          userResponse.user.statusBool = (userResponse.user.status == 'ENABLE' ? true : false);
          return userResponse;
        })
      );
  }



  getUserByUsername(user: User): Observable<UserResponse> {
    return this.http.get<UserResponse>
      (urlEndPoint + "/" + user.username + "/findByUsername",
        {
          responseType: 'json',
          withCredentials: true
        }
      ).pipe(
        map(response => {
          let userResponse = response as UserResponse;
          if (userResponse.user != null) {
            userResponse.user.statusBool = (userResponse.user.status == 'ENABLE' ? true : false);
          }
          return userResponse;
        })
      );
  }

  postUser(userResponse: UserResponse): Observable<UserResponse> {
    return this.http.post<UserResponse>
      (urlEndPoint, userResponse,
        {
          responseType: 'json',
          withCredentials: true
        }
      ).pipe(
        map(response => {
          let userResponse = response as UserResponse;
          userResponse.user.statusBool = (userResponse.user.status == 'ENABLE' ? true : false);
          return userResponse;
        })
      );
  }

}
