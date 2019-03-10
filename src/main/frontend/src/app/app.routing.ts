import { Routes, RouterModule } from '@angular/router';


import { LoginComponent } from './login';

import { RestoDetailComponent } from './resto-detail';
import { DashboardComponent } from './dashboard';
import { AuthGuard } from './_guards';
import { NgModule } from '@angular/core';
import { MapComponent } from './map';


const appRoutes: Routes = [
   // { path: '', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'login', component: LoginComponent },
    { path: 'add', component: RestoDetailComponent },
    { path: 'detail/:id', component: RestoDetailComponent },
    { path: 'dashboard', component: DashboardComponent },
    { path: 'map', component: MapComponent },
    // otherwise redirect to home
    { path: '**', redirectTo: 'dashboard' }
];

export const routing = RouterModule.forRoot(appRoutes, { useHash: true, onSameUrlNavigation: "reload"});