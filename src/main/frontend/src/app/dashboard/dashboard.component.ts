import { Component, OnInit,  Input,  OnChanges, SimpleChange } from '@angular/core';
import { RestoService, VoteService, AuthenticationService } from '../_services';
import { Resto, Utilisateur} from '../_models';



@Component({
  providers: [RestoService],
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent  implements OnChanges{
  restos: Resto[] = [];

  @Input() utilisateur: Utilisateur;

  constructor(private restoService: RestoService) {
    this.utilisateur = JSON.parse(localStorage.getItem('currentUtilisateur'));
    console.log(this.utilisateur);
    this.getRestos();
  }

  changeLog: string[] = [];
 
  ngOnChanges(changes: {[propKey: string]: SimpleChange}) {
    console.log(changes);
    let log: string[] = [];
    for (let propName in changes) {
      let changedProp = changes[propName];
      let to = JSON.stringify(changedProp.currentValue);
      if (changedProp.isFirstChange()) {
        log.push(`Initial value of ${propName} set to ${to}`);
      } else {
        let from = JSON.stringify(changedProp.previousValue);
        log.push(`${propName} changed from ${from} to ${to}`);
      }
    }
    this.changeLog.push(log.join(', '));
    console.log(this.changeLog);
  }

  getRestos(): void {
    this.restoService.getAll().subscribe(restoId => {
      for (var num of restoId) {
        this.restoService.getById(this.utilisateur?this.utilisateur.id:null, num).subscribe(resto => {
          this.restos.push(resto);
        })
      }
    });
  }

  orderByDate(array: any[], prop: string, reverse?: boolean) {
    // Order an array of objects by a date property
    // Default: ascending (1992->2017 | Jan->Dec)
    if (!prop) {
      return array;
    }
    const sortedArray = array.sort((a, b) => {
      const dateA = new Date(a[prop]).getTime();
      const dateB = new Date(b[prop]).getTime();
      return !reverse ? dateA - dateB : dateB - dateA;
    });
    return sortedArray;
  }
}
