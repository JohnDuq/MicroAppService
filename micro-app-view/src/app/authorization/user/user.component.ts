import { UserGetResponse } from './../../model/UserGetResponse';
import { UserService } from './user.service';
import { User } from './../../model/User';
import { Component, OnInit } from '@angular/core';
import { UserResponse } from 'src/app/model/UserResponse';
import { RoleService } from '../role/role.service';
import { Role } from 'src/app/model/Role';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html'
})
export class UserComponent implements OnInit {

  idUserSelectedDisable: boolean;
  userSelected: UserResponse;
  listUsers: User[];

  constructor(private userService: UserService, private roleService: RoleService) { }

  ngOnInit() {
    this.clean();
    this.getListUsers();
  }

  public clean(): void {
    this.userSelected = new UserResponse();
    this.userSelected.user = new User();
    this.getListRole();
    this.userSelected.listRolesUser = new Array();
    this.idUserSelectedDisable = false;
  }

  public getListUsers(): void {
    this.userService.getUsers().subscribe(
      (response) => this.listUsers = response.listUsers
    );
  }

  public saveUser(): void {
    if (this.userSelected.user.statusBool) {
      this.userSelected.user.status = 'ENABLE';
    } else {
      this.userSelected.user.status = 'DISABLE';
    }
    this.userService.postUser(this.userSelected).subscribe(
      (response) => {
        this.userSelected = response;
      }
    );
  }

  public editUser(user: User): void {
    this.idUserSelectedDisable = true;
    this.userService.getUser(user).subscribe(
      (response) => {
        this.userSelected = response;
      }
    );
  }

  public getListRole(): void {
    this.roleService.getRoles().subscribe(
      (response) => this.userSelected.listRolesAvaible = response.listRoles
    );
  }

}
