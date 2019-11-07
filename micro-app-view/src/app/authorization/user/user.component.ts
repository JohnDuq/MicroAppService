import { AppComponent } from './../../app.component';
import { UserService } from './user.service';
import { User } from './../../model/User';
import { Component, OnInit } from '@angular/core';
import { UserResponse } from 'src/app/model/UserResponse';
import { RoleService } from '../role/role.service';
import { MessageService } from 'primeng/components/common/messageservice';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  providers: [MessageService]
})
export class UserComponent implements OnInit {

  userValidateSave: boolean;
  userSelected: UserResponse;
  listUsers: User[];

  constructor(
    private appComponent: AppComponent,
    private userService: UserService,
    private roleService: RoleService
  ) { }

  ngOnInit() {
    this.clean();
    this.getListUsers();
  }

  public clean(): void {
    this.userSelected = new UserResponse();
    this.userSelected.user = new User();
    this.getListRole();
    this.getListUsers();
    this.userSelected.listRolesUser = new Array();
  }

  public getListUsers(): void {
    this.userService.getUsers().subscribe(
      (response) => this.listUsers = response.listUsers
    );
  }

  public saveUser(form: NgForm): void {
    this.appComponent.blockDocument();
    if (form.valid) {
      this.userSelected.user.username = this.userSelected.user.username.toUpperCase();
      this.userSelected.user.status = this.userSelected.user.statusBool ? 'ENABLE' : 'DISABLE';
      this.userService.getUserByUsername(this.userSelected.user).subscribe(
        (response) => {
          let userFindUsername = response.user;
          if (this.userSelected.user.idUser == null
            || this.userSelected.user.idUser == undefined) { //NEW USER
            if (userFindUsername != null && userFindUsername != undefined) {
              this.appComponent.addMessageErrorSummary('Username already exist');
            } else {
              this.userService.postUser(this.userSelected).subscribe(
                (responsePost: UserResponse) => {
                  this.userSelected = responsePost;
                  this.appComponent.showMessageResponse(responsePost);
                  this.getListUsers();
                }
              );
            }
          } else { //USER EXIST
            if (userFindUsername != null && userFindUsername != undefined) {
              if (userFindUsername.idUser != this.userSelected.user.idUser) {
                this.appComponent.addMessageErrorSummary('Username already exist');
              } else {
                this.userService.putUser(this.userSelected).subscribe(
                  (responsePost: UserResponse) => {
                    this.userSelected = responsePost;
                    this.appComponent.showMessageResponse(responsePost);
                    this.getListUsers();
                  }
                );
              }
            } else {
              this.userService.putUser(this.userSelected).subscribe(
                (responsePost: UserResponse) => {
                  this.userSelected = responsePost;
                  this.appComponent.showMessageResponse(responsePost);
                  this.getListUsers();
                }
              );
            }
          }
          this.appComponent.unblockDocument();
        }
      );
    }
    this.appComponent.unblockDocument();
  }

  public editUser(user: User): void {
    this.userService.getUser(user).subscribe(
      (response: UserResponse) => {
        this.userSelected = response;
      }
    );
  }

  public deleteUser(): void {
    console.log(this.userSelected);
    this.appComponent.blockDocument();
    this.userService.deleteUser(this.userSelected.user).subscribe(
      (responsePost: UserResponse) => {
        this.clean();
        this.appComponent.unblockDocument();
        this.appComponent.showMessageResponse(responsePost);
      }
    );
  }

  public deleteUserList(user: User): void {
    this.appComponent.blockDocument();
    this.userService.deleteUser(user).subscribe(
      (responsePost: UserResponse) => {
        this.clean();
        this.appComponent.unblockDocument();
        this.appComponent.showMessageResponse(responsePost);
      }
    );
  }

  public getListRole(): void {
    this.roleService.getRoles().subscribe(
      (response) => this.userSelected.listRolesAvaible = response.listRoles
    );
  }

}
