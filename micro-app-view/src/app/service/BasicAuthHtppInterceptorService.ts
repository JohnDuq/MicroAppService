import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})

export class BasicAuthHtppInterceptorService implements HttpInterceptor {

    constructor() { }

    intercept(httpRequest: HttpRequest<any>, next: HttpHandler) {

        if (sessionStorage.getItem('username') && sessionStorage.getItem('Authorization')) {
            httpRequest = httpRequest.clone({
                setHeaders: {
                    'Content-Type': 'application/json',
                    Authorization: sessionStorage.getItem('Authorization')
                }
            });
        }
        return next.handle(httpRequest);
    }
}
