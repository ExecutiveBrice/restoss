import { Injectable } from '@angular/core';
import { HttpClient,HttpParams } from '@angular/common/http';

import { environment } from '../../environments/environment';
import { Resto } from '../_models';

@Injectable()
export class RestoService {
    constructor(private http: HttpClient) { }

    getAll() {
        return this.http.get<number[]>(`${environment.apiUrl}restos/liste`);
    }

    getById(userId: number, id: number) {
        //let params = new HttpParams().set('id', ''+id+'');
        //params.set('userId', ''+userId+'');
        //console.log(params)
        let userIdString = userId!=null?userId:"";
        return this.http.get<Resto>(`${environment.apiUrl}restos/` ,{params:{"id":''+id+'',"userId":''+userIdString+''}});
    }

    getMoyenneForResto(restoId: number) {
        let params = new HttpParams().set('id', ''+restoId+'');
        return this.http.get<Number>(`${environment.apiUrl}restos/moyenne` ,{params});
    }

    getFull(id: number) {
        let params = new HttpParams().set('id', ''+id+'');
        return this.http.get<Resto>(`${environment.apiUrl}restos/restoFull` ,{params});
    }
    add(resto: Resto) {
        return this.http.post<Resto>(`${environment.apiUrl}restos/add`, resto);
    }

    update(resto: Resto) {
        return this.http.post<Resto>(`${environment.apiUrl}restos/update`, resto);
    }

    delete(id: number) {
        return this.http.delete<Number>(`${environment.apiUrl}restos/` + id);
    }
}