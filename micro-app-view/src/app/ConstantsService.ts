import { Injectable } from '@angular/core';

@Injectable()
export class ConstantsService {
    //For local
    public readonly baseAppUrl: string = 'http://localhost:8080/api/microappservice';
    //public readonly baseAppUrl: string = 'http://service:8080/api/microappservice';
}
