import { UserGetResponse } from './../../model/UserGetResponse';
import { UserService } from './user.service';
import { User } from './../../model/User';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html'
})
export class UserComponent implements OnInit {

  userSelected: User;
  listUsers: User[];
  checked: boolean;
  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userSelected = new User();
    this.getListUsers();
  }

  public getListUsers(): void {
    this.userService.getUsers().subscribe(
      (response) => this.listUsers = response.listUsers
    );
  }

  public editUser(user: User): void {
    this.userSelected = new User();
    this.userSelected.idUser = user.idUser;
    this.userSelected.username = user.username;
    this.userSelected.status = user.status;
    this.userSelected.statusBool = user.status == 'ENABLE';
  }

}
