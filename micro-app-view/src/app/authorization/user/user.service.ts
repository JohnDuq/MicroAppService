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

  private headerAuth = new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': 'Bearer eyJhbGciOiJIUzM4NCJ9.eyJhdXRob3JpdGllcyI6Ilt7XCJhdXRob3JpdHlcIjpcIlJPTEVfQURNSU5cIn1dIiwic3ViIjoiUk9PVCIsImlhdCI6MTU3MjYxNTU1NiwiZXhwIjoxNTcyNjU4NzU2fQ.b0NO5UGdBYbe4hea2geRz7TqwhbR9dBXJwOJMcIMd3jlE9-0UxU5XnzFaiED9dA0'
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
          headers: this.headerAuth,
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
          headers: this.headerAuth,
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
          headers: this.headerAuth,
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
