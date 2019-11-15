import { AuthenticationService } from './../../service/Authentication';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html'
})
export class HeaderComponent implements OnInit {
    title: string = 'app';
    constructor(private router: Router,public loginService: AuthenticationService) { }

    ngOnInit() {
        
    }

    public getUsername(): string {
        return sessionStorage.getItem('username');
    }

    logOut(): void {
        this.loginService.logOut();
        this.router.navigate(['login']);
    }
}