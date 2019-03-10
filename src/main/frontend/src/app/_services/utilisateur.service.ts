import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Subject } from 'rxjs';
import { environment } from '../../environments/environment';
import { Utilisateur } from '../_models';

@Injectable()
export class UtilisateurService {
    constructor(private http: HttpClient) {     console.log('UtilisateurService')}


    // Observable string sources
    private utilisateurSource = new Subject<Utilisateur>();

    // Observable string streams
    utilisateurSource$ = this.utilisateurSource.asObservable();

    // Service message commands
    sourceUtilisateur(utilisateur: Utilisateur) {
        console.log('sourceUtilisateur')
        console.log(utilisateur)
        this.utilisateurSource.next(utilisateur);
    }


    getAll() {
        return this.http.get<number[]>(`${environment.apiUrl}utilisateur/liste`);
    }

    getById(id: number) {
        let params = new HttpParams().set('id', '' + id + '');
        return this.http.get<Utilisateur>(`${environment.apiUrl}utilisateur/`, { params });
    }

    register(utilisateur: Utilisateur) {
        return this.http.post<Utilisateur>(`${environment.apiUrl}utilisateur/register`, utilisateur);
    }

    update(utilisateur: Utilisateur) {
        return this.http.put<Utilisateur>(`${environment.apiUrl}utilisateur/` + utilisateur.id, utilisateur);
    }

    delete(id: number) {
        return this.http.delete<Number>(`${environment.apiUrl}utilisateur/` + id);
    }
}