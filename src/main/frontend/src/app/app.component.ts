import { Component } from '@angular/core';
import { Utilisateur } from './_models';
import { AlertService, AuthenticationService, UtilisateurService } from './_services';
import { Router } from '@angular/router';


@Component({
    selector: 'app',
    templateUrl: 'app.component.html'
})

export class AppComponent {
    utilisateur: Utilisateur = null;
coucou : String = "coucou";


    constructor(private authenticationService: AuthenticationService,
        private utilisateurService: UtilisateurService,
        public router: Router) {
        this.utilisateur = JSON.parse(localStorage.getItem('currentUtilisateur'));
        console.log('AppComponent')
        utilisateurService.utilisateurSource$.subscribe(
            datac => {
                if (datac != undefined) {
                    this.utilisateur = datac;
                    console.log(this.utilisateur)
                }
            });
        console.log(this.utilisateur)
        this.utilisateurService.sourceUtilisateur(this.utilisateur);
    }

    ngOnInit() {


    }


    logout() {
        console.log("appcomp logout")
        localStorage.removeItem('currentUtilisateur');
        this.utilisateur = null;
        this.router.navigate(['/login']);
        /**
        this.authenticationService.logout().subscribe(data => {
            console.log(data)
            this.utilisateur = null;
        }),
        error => {
            console.log('😢 Oh no!', error);
        }
        */
    }

}