import { Injectable } from '@angular/core';
import {BaseServiceService} from '../../shared/services/base-service.service';

@Injectable({
  providedIn: 'root'
})
export class PatientService extends BaseServiceService {
    private endpoint: string = 'patients';

    public allByClientId(clientId: number): Promise<any> {
        return this.http.get(`${this.apiUrl + this.endpoint}/client/${clientId}`, this.getHeaders()).toPromise();
    }

    public get(): Promise<any> {
        return this.http.get(this.apiUrl + this.endpoint, this.getHeaders()).toPromise();
    }

    public async insert(data: any = {}): Promise<any> {
        try {
            await this.http.post(this.apiUrl + this.endpoint, data, this.getHeaders()).toPromise();
        } catch (e) {
            throw new Error(this.unwrapError(e));
        }
    }

    public async delete(key: number): Promise<any> {
        try {
            await this.http.delete(`${this.apiUrl + this.endpoint}/${key}`, this.getHeaders()).toPromise();
        } catch (e) {
            throw new Error(this.unwrapError(e));
        }
    }

    public async update(key: number, data: any = {}): Promise<any> {
        try {
            await this.http.put(`${this.apiUrl + this.endpoint}/${key}`, data, this.getHeaders()).toPromise();
        } catch (e) {
            throw new Error(this.unwrapError(e));
        }
    }
}
