import { AppComponent } from './../app.component';
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

  loginResponse = new LoginResponse();
  username = '';
  password = '';
  invalidLogin = false;

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService,
    private appComponent: AppComponent
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
    this.appComponent.blockDocument();
    this.authenticationService.authenticate(this.username, this.password).subscribe(
      (response) => {
        this.loginResponse = response;
        if (this.loginResponse.token == null || this.loginResponse.token === undefined || this.loginResponse.token === '') {
          this.appComponent.addMessageComplete('error', 'Error Message', 'Bad credentials');
          sessionStorage.removeItem('Authorization');
          sessionStorage.removeItem('username');
        } else {
          sessionStorage.setItem('username', this.loginResponse.user);
          let tokenStr = 'Bearer ' + this.loginResponse.token;
          sessionStorage.setItem('Authorization', tokenStr);
          this.router.navigate(['home']);
        }
        this.appComponent.unblockDocument();
      }
    );
  }
}
