import { AuthenticationService } from './../../service/Authentication';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html'
})
export class HeaderComponent implements OnInit {
    title: string = 'Micro-App-View';
    user: string;
    constructor(private router: Router,private loginService: AuthenticationService) { }

    ngOnInit() {
        this.user = sessionStorage.getItem('username');
    }

    logOut(): void {
        this.loginService.logOut();
        this.router.navigate(['login']);
    }
}