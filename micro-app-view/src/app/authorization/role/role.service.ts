import { RoleResponse } from '../../model/RoleResponse';
import { Injectable } from '@angular/core';
import { Role } from '../../model/Role';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { RoleGetResponse } from '../../model/RoleGetResponse';

const urlEndPoint = "http://localhost:8080/api/microappservice/role";

@Injectable()
export class RoleService {

  private headerAuth = new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': 'Bearer eyJhbGciOiJIUzM4NCJ9.eyJhdXRob3JpdGllcyI6Ilt7XCJhdXRob3JpdHlcIjpcIlJPTEVfQURNSU5cIn1dIiwic3ViIjoiUk9PVCIsImlhdCI6MTU3MjYxNTU1NiwiZXhwIjoxNTcyNjU4NzU2fQ.b0NO5UGdBYbe4hea2geRz7TqwhbR9dBXJwOJMcIMd3jlE9-0UxU5XnzFaiED9dA0'
  });

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

  postRole(role: Role): Observable<RoleResponse> {
    return this.http.post<RoleResponse>
      (urlEndPoint, role,
        {
          headers: this.headerAuth,
          responseType: 'json',
          withCredentials: true
        }
      );
  }

  putRole(role: Role): Observable<RoleResponse> {
    return this.http.put<RoleResponse>
      (urlEndPoint, role,
        {
          headers: this.headerAuth,
          responseType: 'json',
          withCredentials: true
        }
      );
  }

  deleteRole(role: Role): Observable<RoleResponse> {
    return this.http.delete<RoleResponse>
      (urlEndPoint + '/' + role.idRole,
        {
          headers: this.headerAuth,
          responseType: 'json',
          withCredentials: true
        }
      );
  }

}
