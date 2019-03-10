import { Injectable } from '@angular/core';
import { Utilisateur } from '../_models';
import { HttpClient, HttpParams } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
    constructor(private http: HttpClient) { }

    login(utilisateurname: string, password: string) {

        return this.http.post<any>(`${environment.apiUrl}/login`, { utilisateurname, password })
            .pipe(map(utilisateur => {
                console.log("response")
                console.log(utilisateur)
                // login successful if there's a utilisateur in the response
                if (utilisateur) {
                    // store utilisateur details and basic auth credentials in local storage 
                    // to keep utilisateur logged in between page refreshes
                    utilisateur.authdata = window.btoa(utilisateurname + ':' + password);
                    localStorage.setItem('currentUtilisateur', JSON.stringify(utilisateur));
                }

                return utilisateur;
            }));
    }

    logout() {
        // remove utilisateur from local storage to log utilisateur out
       

        var currentUtilisateur :Utilisateur = JSON.parse(localStorage.getItem('currentUtilisateur'));
        localStorage.removeItem('currentUtilisateur');

        return this.http.get(`${environment.apiUrl}/deconnexion`).pipe(map(data => {
            console.log("response")
            console.log(data)
            // login successful if there's a utilisateur in the response

            return data;
        }));
        /**
        return this.http.get(`${environment.apiUrl}/login`)
        .pipe(map(data => {
            console.log("response")
            console.log(data)
            // login successful if there's a utilisateur in the response

            return data;
        }));
        */
    }
}