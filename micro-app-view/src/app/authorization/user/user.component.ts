import { MessageUtil } from './../../util/MessageUtil';
import { UserGetResponse } from './../../model/UserGetResponse';
import { UserService } from './user.service';
import { User } from './../../model/User';
import { Component, OnInit } from '@angular/core';
import { UserResponse } from 'src/app/model/UserResponse';
import { RoleService } from '../role/role.service';
import { Role } from 'src/app/model/Role';
import { MessageService } from 'primeng/components/common/messageservice';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  providers: [MessageService]
})
export class UserComponent implements OnInit {

  messageUtil: MessageUtil = new MessageUtil();
  blockedDocument: boolean = false;
  idUserSelectedDisable: boolean;
  userValidateSave: boolean;
  userSelected: UserResponse;
  listUsers: User[];

  constructor(
    private userService: UserService,
    private roleService: RoleService,
    private messageService: MessageService
  ) { }

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
    this.blockedDocument = true;
    this.userValidateSave = true;
    if (this.userSelected.user.username == null
      || this.userSelected.user.username == undefined
      || this.userSelected.user.username == '') {
      this.messageService.add({ severity: 'error', summary: 'Error Message', detail: 'User cant be empty' });
      this.blockedDocument = false;
    } else {
      this.userService.getUserByUsername(this.userSelected.user).subscribe(
        (response) => {
          let userFindUsername = response.user;
          if (this.userSelected.user.idUser == null
            || this.userSelected.user.idUser == undefined) { //NEW USER
            if (userFindUsername != null && userFindUsername != undefined) {
              this.messageService.add({ severity: 'error', summary: 'Error Message', detail: 'Usuario Existente' });
              this.userValidateSave = false;
            }
          } else { //USER EXIST
            if (userFindUsername != null && userFindUsername != undefined) {
              if (userFindUsername.idUser != this.userSelected.user.idUser) {
                this.messageUtil.showMessageError(this.messageService, 'Username exist');
                this.userValidateSave = false;
              }
            }
          }

          if (this.userSelected.user.password == null || this.userSelected.user.password == '') {
            this.messageUtil.showMessageError(this.messageService, 'Password cant be empty');
            this.userValidateSave = false;
          }

          if (this.userSelected.listRolesUser == null || this.userSelected.user.password == '') {
            this.messageUtil.showMessageError(this.messageService, 'Password cant be empty');
            this.userValidateSave = false;
          }

          if (this.userValidateSave) {
            if (this.userSelected.user.statusBool) {
              this.userSelected.user.status = 'ENABLE';
            } else {
              this.userSelected.user.status = 'DISABLE';
            }
            this.userService.postUser(this.userSelected).subscribe(
              (response) => {
                this.userSelected = response;
                this.messageUtil.showMessagesResponse(this.messageService, response);
                this.getListUsers();
              }
            );
          }
          this.blockedDocument = false;
        }
      );
    }


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
