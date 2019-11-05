import { LoginResponse } from './../model/LoginResponse';
import { AuthenticationService } from './../service/Authentication';

import { Router } from '@angular/router';
import { Component, OnInit, ɵConsole } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginResponse = new LoginResponse();
  username = '';
  password = '';
  invalidLogin = false;

  constructor(private router: Router, private authenticationService: AuthenticationService) { }

  ngOnInit() {
  }

  clean() {
    this.username = '';
    this.password = '';
  }

  checkLogin() {
    this.authenticationService.authenticate(this.username, this.password).subscribe(
      (response) => {
        this.loginResponse = response;
        if (this.loginResponse.token == null || this.loginResponse.token === undefined || this.loginResponse.token === '') {
          console.log('NO AUTENTICADO');
          sessionStorage.removeItem('token');
          sessionStorage.removeItem('username');
        } else {
          console.log('AUTENTICADO');
          console.log(this.loginResponse);
          sessionStorage.setItem('username', this.loginResponse.user);
          let tokenStr = 'Bearer ' + this.loginResponse.token;
          sessionStorage.setItem('token', tokenStr);
          this.router.navigate(['home']);
        }
      }
    );
  }
}
