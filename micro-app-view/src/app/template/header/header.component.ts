import { AuthenticationService } from './../../service/Authentication';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html'
})
export class HeaderComponent implements OnInit {
    title: string = 'Micro-App-View';
    constructor(private router: Router,private loginService: AuthenticationService) { }

    ngOnInit() {

    }

    logOut(): void {
        this.loginService.logOut();
        this.router.navigate(['login']);
    }
}