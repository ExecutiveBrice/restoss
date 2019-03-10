import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { Utilisateur } from '../_models';
import { AlertService, AuthenticationService, UtilisateurService } from '../_services';

@Component({ templateUrl: 'login.component.html' })
export class LoginComponent implements OnInit {
    loginForm: FormGroup;
    loading = false;
    submitted = false;
    returnUrl: string;
    utilisateur: Utilisateur = null;
    utilisateurs: Utilisateur[] = [];
    constructor(
        private utilisateurService: UtilisateurService,
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private authenticationService: AuthenticationService,
        private alertService: AlertService) {
        console.log('LoginComponent')
        this.getUtilisateurs()
    }

    ngOnInit() {
        this.loginForm = this.formBuilder.group({
            utilisateurname: ['', Validators.required],
            password: ['', Validators.required]
        });



        // get return url from route parameters or default to '/'
        this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    }



    getUtilisateurs(): any {
        this.utilisateurService.getAll().subscribe(utilisateurList => {
            for (var utilisateurId of utilisateurList) {
                this.utilisateurService.getById(utilisateurId).subscribe(data => {
                    this.utilisateurs.push(data as Utilisateur);
                }),
                    error => {
                        console.log('😢 Oh no!', error);
                    }
            }
        }),
            error => {
                console.log('😢 Oh no!', error);
            }
    }




    // convenience getter for easy access to form fields
    get f() { return this.loginForm.controls; }

    onSubmit() {
        this.submitted = true;

        // stop here if form is invalid
        if (this.loginForm.invalid) {
            return;
        }


        for (let index = 0; index < this.utilisateurs.length; index++) {

            if (this.utilisateurs[index].ssoId === this.f.utilisateurname.value && this.utilisateurs[index].ssoId === this.f.password.value) {

                localStorage.setItem('currentUtilisateur', JSON.stringify(this.utilisateurs[index]));
                this.utilisateur = this.utilisateurs[index];
                this.utilisateurService.sourceUtilisateur(this.utilisateur);
                this.router.navigate(['/map']);
            }
        };


        /**
        this.loading = true;
        this.authenticationService.login(this.f.utilisateurname.value, this.f.password.value)
            .pipe(first())
            .subscribe(
                data => {
                    this.router.navigate([this.returnUrl]);
                    console.log(JSON.parse(localStorage.getItem('currentUtilisateur')))
                    this.utilisateur = JSON.parse(localStorage.getItem('currentUtilisateur'));
                    this.utilisateurService.sourceUtilisateur(this.utilisateur);
                },
                error => {
                    this.alertService.error(error);
                    this.loading = false;
                });
 
        */
    }











    transform(array, orderBy, asc = true) {

        if (!orderBy || orderBy.trim() == "") {
            return array;
        }

        //ascending
        if (asc) {
            return Array.from(array).sort((item1: any, item2: any) => {
                return this.orderByComparator(item1[orderBy], item2[orderBy]);
            });
        }
        else {
            //not asc
            return Array.from(array).sort((item1: any, item2: any) => {
                return this.orderByComparator(item2[orderBy], item1[orderBy]);
            });
        }

    }

    orderByComparator(a: any, b: any): number {

        if ((isNaN(parseFloat(a)) || !isFinite(a)) || (isNaN(parseFloat(b)) || !isFinite(b))) {
            //Isn't a number so lowercase the string to properly compare
            if (a.toLowerCase() < b.toLowerCase()) return -1;
            if (a.toLowerCase() > b.toLowerCase()) return 1;
        }
        else {
            //Parse strings as numbers to compare properly
            if (parseFloat(a) < parseFloat(b)) return -1;
            if (parseFloat(a) > parseFloat(b)) return 1;
        }

        return 0; //equal each other
    }
}
