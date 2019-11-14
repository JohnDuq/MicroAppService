import { ConstantsService } from './../../ConstantsService';
import { map } from 'rxjs/operators';
import { UserGetResponse } from './../../model/UserGetResponse';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from 'src/app/model/User';
import { UserResponse } from 'src/app/model/UserResponse';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private urlEndPoint: string;

  constructor(private http: HttpClient, private constantsService: ConstantsService) {
    this.urlEndPoint = this.constantsService.baseAppUrl + '/user';
  }

  getUsers(): Observable<UserGetResponse> {
    return this.http.get<UserGetResponse>
      (this.urlEndPoint,
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
      (this.urlEndPoint + "/" + user.idUser,
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
      (this.urlEndPoint + "/" + user.username + "/findByUsername",
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
      (this.urlEndPoint, userResponse,
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

  putUser(userResponse: UserResponse): Observable<UserResponse> {
    return this.http.put<UserResponse>
      (this.urlEndPoint, userResponse,
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

  deleteUser(user: User): Observable<UserResponse> {
    return this.http.delete<UserResponse>
      (this.urlEndPoint + '/' + user.idUser,
        {
          responseType: 'json',
          withCredentials: true
        }
      );
  }

}
