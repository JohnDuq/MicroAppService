import { RoleResponse } from '../../model/RoleResponse';
import { Injectable } from '@angular/core';
import { Role } from '../../model/Role';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { RoleGetResponse } from '../../model/RoleGetResponse';

const urlEndPoint = "http://localhost:8080/api/microappservice/role";

@Injectable()
export class RoleService {

  constructor(private http: HttpClient) { }

  getRoles(): Observable<RoleGetResponse> {
    return this.http.get<RoleGetResponse>
      (urlEndPoint,
        {
          responseType: 'json',
          withCredentials: true
        }
      );
  }

  postRole(role: Role): Observable<RoleResponse> {
    return this.http.post<RoleResponse>
      (urlEndPoint, role,
        {
          responseType: 'json',
          withCredentials: true
        }
      );
  }

  putRole(role: Role): Observable<RoleResponse> {
    return this.http.put<RoleResponse>
      (urlEndPoint, role,
        {
          responseType: 'json',
          withCredentials: true
        }
      );
  }

  deleteRole(role: Role): Observable<RoleResponse> {
    return this.http.delete<RoleResponse>
      (urlEndPoint + '/' + role.idRole,
        {
          responseType: 'json',
          withCredentials: true
        }
      );
  }

}
