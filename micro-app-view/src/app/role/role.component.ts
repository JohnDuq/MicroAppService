import { Component, OnInit } from '@angular/core';
import { Role } from '../model/Role';
import { RoleService } from './role.service';
import { SelectItem } from 'primeng/components/common/selectitem';

@Component({
  selector: 'app-role',
  templateUrl: './role.component.html'
})
export class RoleComponent implements OnInit {

  rolePrincipal: Role;
  lRoles: Role[];

  constructor(private roleService: RoleService) { }

  ngOnInit() {
    this.rolePrincipal = new Role();
    this.roleService.getRoles().subscribe(
      (response) => this.lRoles = response.listRoles
    );
  }

  public saveRole(): void {

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

}
