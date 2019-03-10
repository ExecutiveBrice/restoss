import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

import { environment } from '../../environments/environment';
import { Vote } from '../_models';

@Injectable()
export class VoteService {
    constructor(private http: HttpClient) { }

    getAll() {
        return this.http.get<number[]>(`${environment.apiUrl}votes/liste`);
    }

    getById(id: number) {
        let params = new HttpParams().set('id', '' + id + '');
        return this.http.get<Vote>(`${environment.apiUrl}votes/`, { params });
    }

    getByRestoId(id: number) {
        let params = new HttpParams().set('id', '' + id + '');
        return this.http.get<Vote[]>(`${environment.apiUrl}votes/byResto`, { params });
    }

    add(restoId: number, userId: number) {
        let params = new HttpParams().set('id', '' + restoId + '');
        return this.http.post<Vote>(`${environment.apiUrl}votes/add`, userId, { params });
    }

    update(restoId: number, vote: Vote) {
        let params = new HttpParams().set('id', '' + restoId + '');
        return this.http.post<Vote>(`${environment.apiUrl}votes/update`, vote, { params });
    }

    delete(id: number) {
        return this.http.delete<Number>(`${environment.apiUrl}votes/` + id);
    }
}