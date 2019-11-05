import { AuthenticationService } from './../../service/Authentication';
import { Component } from '@angular/core';
@Component({
    selector: 'app-footer',
    templateUrl: './footer.component.html',
    styleUrls: ['./footer.component.css']
})
export class FooterComponent {
    description: string = 'Example Project for start';
    author: string = 'Jonathan Duque Ramos';
    twitter: string = '@JDuqueRamos';

    constructor(private loginService: AuthenticationService) { }
}
