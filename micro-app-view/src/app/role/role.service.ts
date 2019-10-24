import { Injectable } from '@angular/core';
import { Role } from '../model/Role';
import { of, Observable, from } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { RoleGetResponse } from '../model/RoleGetResponse';

const urlEndPoint = "http://localhost:8080/api/microappservice/role";

@Injectable()
export class RoleService {

  private headerAuth = new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': 'Bearer eyJhbGciOiJIUzM4NCJ9.eyJhdXRob3JpdGllcyI6Ilt7XCJhdXRob3JpdHlcIjpcIlJPTEVfQURNSU5cIn1dIiwic3ViIjoiUk9PVCIsImlhdCI6MTU3MTkyOTY0MCwiZXhwIjoxNTcxOTMzMjQwfQ.SIf10E6THu3xuBRUH2yRsCufvmAJx6TM8cN9mbZNWrDzyCjxxSFNz7dBz6T33mrX'
  });
  lRoles: Role[];

  constructor(private http: HttpClient) { }

  getRoles(): Observable<RoleGetResponse> {
    return this.http.get<RoleGetResponse>
    (urlEndPoint,
      {
        headers: this.headerAuth,
        responseType: 'json',
        withCredentials: true
      }
    );
  }

}
