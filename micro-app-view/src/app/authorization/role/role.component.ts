import { Role } from '../../model/Role';
import { Component, OnInit } from '@angular/core';
import { RoleService } from './role.service';

@Component({
  selector: 'app-role',
  templateUrl: './role.component.html'
})
export class RoleComponent implements OnInit {

  blockedDocument: boolean = false;
  rolePrincipal: Role;
  lRoles: Role[];

  constructor(private roleService: RoleService) { }

  ngOnInit() {
    this.rolePrincipal = new Role();
    this.getListRole();
  }

  public saveRole(): void {
    this.blockedDocument = true;
    if (this.rolePrincipal.idRole == null) {
      this.roleService.postRole(this.rolePrincipal).subscribe(
        (response) => {
          this.rolePrincipal = response.role;
          this.getListRole();
          this.blockedDocument = false;
        }
      );
    } else {
      this.roleService.putRole(this.rolePrincipal).subscribe(
        (response) => {
          this.rolePrincipal = response.role;
          this.getListRole();
          this.blockedDocument = false;
        }
      );
    }
  }

  public deleteRole(): void {
    this.blockedDocument = true;
    this.roleService.deleteRole(this.rolePrincipal).subscribe(
      (response) => {
        this.clean();
        this.getListRole();
        this.blockedDocument = false;
      }
    );
  }

  public deleteRoleTable(roleDelete: Role): void {
    this.blockedDocument = true;
    this.roleService.deleteRole(roleDelete).subscribe(
      (response) => {
        this.clean();
        this.getListRole();
        this.blockedDocument = false;
      }
    );
  }

  public editRole(roleEdit: Role): void {
    this.rolePrincipal = new Role();
    this.rolePrincipal.idRole = roleEdit.idRole;
    this.rolePrincipal.name = roleEdit.name;
    this.rolePrincipal.description = roleEdit.description;
  }

  public clean(): void {
    this.rolePrincipal = new Role();
  }

  public getListRole(): void {
    this.roleService.getRoles().subscribe(
      (response) => this.lRoles = response.listRoles
    );
  }

}
