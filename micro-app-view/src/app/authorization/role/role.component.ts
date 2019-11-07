import { AppComponent } from './../../app.component';
import { Role } from '../../model/Role';
import { Component, OnInit } from '@angular/core';
import { RoleService } from './role.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-role',
  templateUrl: './role.component.html'
})
export class RoleComponent implements OnInit {

  rolePrincipal: Role;
  lRoles: Role[];

  constructor(
    private appComponent: AppComponent,
    private roleService: RoleService) { }

  ngOnInit() {
    this.rolePrincipal = new Role();
    this.getListRole();
  }

  public saveRole(form: NgForm): void {
    if (form.valid) {
      this.appComponent.blockDocument();
      if (this.rolePrincipal.idRole == null) {
        this.roleService.postRole(this.rolePrincipal).subscribe(
          (response) => {
            this.rolePrincipal = response.role;
            this.getListRole();
            this.appComponent.unblockDocument();
            this.appComponent.showMessageResponse(response);
          }
        );
      } else {
        this.roleService.putRole(this.rolePrincipal).subscribe(
          (response) => {
            this.rolePrincipal = response.role;
            this.getListRole();
            this.appComponent.unblockDocument();
            this.appComponent.showMessageResponse(response);
          }
        );
      }
    }
  }

  public deleteRole(): void {
    this.appComponent.blockDocument();
    this.roleService.deleteRole(this.rolePrincipal).subscribe(
      (response) => {
        this.clean();
        this.getListRole();
        this.appComponent.unblockDocument();
        this.appComponent.showMessageResponse(response);
      }
    );
  }

  public deleteRoleTable(roleDelete: Role): void {
    this.appComponent.blockDocument();
    this.roleService.deleteRole(roleDelete).subscribe(
      (response) => {
        this.clean();
        this.getListRole();
        this.appComponent.unblockDocument();
        this.appComponent.showMessageResponse(response);
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
