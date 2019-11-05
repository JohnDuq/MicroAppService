import { MessageUtil } from './../util/MessageUtil';
import { LoginResponse } from './../model/LoginResponse';
import { AuthenticationService } from './../service/Authentication';

import { Router } from '@angular/router';
import { Component, OnInit, ɵConsole } from '@angular/core';
import { MessageService } from 'primeng/components/common/messageservice';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [MessageService]
})
export class LoginComponent implements OnInit {

  messageUtil: MessageUtil = new MessageUtil();
  blockedDocument: boolean = false;
  loginResponse = new LoginResponse();
  username = '';
  password = '';
  invalidLogin = false;

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService,
    private messageService: MessageService
    ) { }

  ngOnInit() {
    if (this.authenticationService.isUserLoggedIn()){
      this.router.navigate(['home']);
    }
  }

  clean() {
    this.username = '';
    this.password = '';
  }

  checkLogin() {
    this.blockedDocument = true;
    this.authenticationService.authenticate(this.username, this.password).subscribe(
      (response) => {
        this.loginResponse = response;
        if (this.loginResponse.token == null || this.loginResponse.token === undefined || this.loginResponse.token === '') {
          this.messageService.add({ severity: 'error', summary: 'Error Message', detail: 'Bad credentials' });
          sessionStorage.removeItem('Authorization');
          sessionStorage.removeItem('username');
        } else {
          sessionStorage.setItem('username', this.loginResponse.user);
          let tokenStr = 'Bearer ' + this.loginResponse.token;
          sessionStorage.setItem('Authorization', tokenStr);
          this.router.navigate(['home']);
        }
        this.blockedDocument = false;
      }
    );
  }
}
