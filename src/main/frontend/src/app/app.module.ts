import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule }    from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppComponent }  from './app.component';
import { routing }        from './app.routing';
import { FormsModule } from '@angular/forms';
import { Ng2ImgMaxModule } from 'ng2-img-max';

import { AlertComponent } from './_directives';
import { AuthGuard } from './_guards';
import { ErrorInterceptor } from './_helpers';
import { AlertService, AuthenticationService, UtilisateurService, FilterSortService, RestoService, VoteService, MapService } from './_services';

import { LoginComponent } from './login';
import { RestoDetailComponent } from './resto-detail';
import { DashboardComponent } from './dashboard';
import { MapComponent } from './map';


@NgModule({
    imports: [
        BrowserModule,
        ReactiveFormsModule,
        HttpClientModule,
        routing,
        FormsModule,
        Ng2ImgMaxModule
    ],
    declarations: [
        AppComponent,
        AlertComponent,
        LoginComponent,
        RestoDetailComponent,
        DashboardComponent,
        MapComponent
    ],
    providers: [
        AuthGuard,
        FilterSortService,
        AlertService,
        AuthenticationService,
        UtilisateurService,
        { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
        RestoService,
        VoteService,
        MapService
    ],
    bootstrap: [AppComponent]
})

export class AppModule { }