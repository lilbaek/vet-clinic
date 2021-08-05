import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {environment} from '../../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export abstract class BaseServiceService {
    constructor(protected http: HttpClient) {
    }

    protected get apiUrl(): string {
        return environment.apiUrl;
    }

    protected getHeaders(): { headers?: HttpHeaders | { [header: string]: string | string[]; } } {
        const headers = new HttpHeaders();
        const httpOptions = {
            withCredentials: false,
            headers: headers
        };
        return httpOptions;
    }

    protected unwrapError(error: any): string {
        return error.error.message;
    }
}
