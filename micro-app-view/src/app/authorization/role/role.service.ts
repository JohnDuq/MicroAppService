import { ConstantsService } from './../../ConstantsService';
import { RoleResponse } from '../../model/RoleResponse';
import { Injectable } from '@angular/core';
import { Role } from '../../model/Role';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { RoleGetResponse } from '../../model/RoleGetResponse';

@Injectable()
export class RoleService {

  private urlEndPoint: string;

  constructor(private http: HttpClient, private constantsService: ConstantsService) {
    this.urlEndPoint = this.constantsService.baseAppUrl + '/role';
   }

  getRoles(): Observable<RoleGetResponse> {
    return this.http.get<RoleGetResponse>
      (this.urlEndPoint,
        {
          responseType: 'json',
          withCredentials: true
        }
      );
  }

  postRole(role: Role): Observable<RoleResponse> {
    return this.http.post<RoleResponse>
      (this.urlEndPoint, role,
        {
          responseType: 'json',
          withCredentials: true
        }
      );
  }

  putRole(role: Role): Observable<RoleResponse> {
    return this.http.put<RoleResponse>
      (this.urlEndPoint, role,
        {
          responseType: 'json',
          withCredentials: true
        }
      );
  }

  deleteRole(role: Role): Observable<RoleResponse> {
    return this.http.delete<RoleResponse>
      (this.urlEndPoint + '/' + role.idRole,
        {
          responseType: 'json',
          withCredentials: true
        }
      );
  }

}
