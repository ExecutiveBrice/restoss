import { Component, OnInit } from "@angular/core";
import * as L from "leaflet";
import { RestoService, MapService } from '../_services';
import { Router } from '@angular/router';
import { Resto, Vote, Utilisateur, Location } from '../_models';

@Component({
  selector: "app-map",
  templateUrl: "./map.component.html",
  styleUrls: ["./map.component.css"]
})
export class MapComponent implements OnInit {
  utilisateur:Utilisateur

  constructor(
    private mapService: MapService,
    private restoService: RestoService,
    private router: Router
  ) {
    this.utilisateur = JSON.parse(localStorage.getItem('currentUtilisateur'));
  }

  ngOnInit() {
        const map = L.map("map", {
          zoomControl: false,
          center: L.latLng(47.2155, -1.5445),
          zoom: 15,
          minZoom: 10,
          maxZoom: 18,
          layers: L.tileLayer(
            "http://{s}.tile.openstreetmap.fr/hot/{z}/{x}/{y}.png",
            {
              zIndex: 1,
              attribution: "."
            }
          )
        });
        var tooltipPopup;
        L.control.zoom({ position: "topright" }).addTo(map);
        L.control.layers(this.mapService.baseMaps).addTo(map);
        L.control.scale().addTo(map);

        this.restoService.getAll().subscribe(restoId => {
          for (var num of restoId) {
            this.restoService.getById(this.utilisateur?this.utilisateur.id:null, num).subscribe(resto => {
              if(resto.latitude && resto.longitude){
                L.marker([ resto.latitude, resto.longitude], {
                  icon: L.icon({
                     iconSize: [ 25, 41 ],
                     iconAnchor: [ 13, 41 ],
                     iconUrl: resto.rating>4?'assets/map_marker-red.png':resto.rating>3?'assets/map_marker-orange1.png':resto.rating>2?'assets/map_marker-orange2.png':resto.rating>1?'assets/map_marker-orange3.png':resto.rating>0?'assets/map_marker-orange4.png':'assets/map_marker-grey.png'})
                  }).addTo(map).on('mouseover', function(e) { 
                    tooltipPopup = L.popup({ offset: L.point(0, -30)});
                            tooltipPopup.setContent(resto.nom);
                            tooltipPopup.setLatLng(e.target.getLatLng());
                            tooltipPopup.openOn(map);
                
                    }).on('mouseout', function(e) { 
                      map.closePopup(tooltipPopup);
                  }).on('click', (e)=>{this.redirection(resto)});;
                }
            })
          }
        });

        this.mapService.map = map;
  }




  redirection(resto: Resto){
    this.router.navigate(['/detail/', resto.id]);
  }
}