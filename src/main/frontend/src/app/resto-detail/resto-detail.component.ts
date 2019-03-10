import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Resto, Vote, Utilisateur, Location } from '../_models';
import { RestoService, VoteService, AuthenticationService, MapService } from '../_services';
import { Ng2ImgMaxService } from 'ng2-img-max';
import { DomSanitizer } from '@angular/platform-browser';
import * as L from 'leaflet';


@Component({
  selector: 'app-resto-detail',
  templateUrl: './resto-detail.component.html',
  styleUrls: ['./resto-detail.component.css']
})
export class RestoDetailComponent implements OnInit {
  resto: Resto = new Resto();
  inputName: Number;
  nouveau: Boolean;
  modifvote: Boolean;
  now: number;
  @Input() utilisateur: Utilisateur;
  monvote: Vote = null;
  uploadedImage: File;
  spinner_overlay = false;
  absents: Vote[] = [];
  modification: Boolean;

  constructor(
    private mapService: MapService,
    private route: ActivatedRoute,
    private restoService: RestoService,
    private voteService: VoteService,
    public authenticationService: AuthenticationService,
    public router: Router,
    private ng2ImgMax: Ng2ImgMaxService,
    public sanitizer: DomSanitizer) {
    this.utilisateur = JSON.parse(localStorage.getItem('currentUtilisateur'));
  }

  ngOnInit(): void {
    let aujourdhui: Date = new Date();
    console.log(aujourdhui);

    this.now = new Date(aujourdhui.getUTCFullYear(), aujourdhui.getUTCMonth(), aujourdhui.getUTCDate(), 13, 0).getTime();
    console.log( this.now);
    
    if (this.route.snapshot.paramMap.get('id') != null) {
      this.getResto();
    } else {
      this.nouveau = true
    }
  }

  getVotes(id: number): void {
    this.spinner_overlay = true;
    this.voteService.getByRestoId(id).subscribe(data => {
      console.log(data)
      this.spinner_overlay = false;
      this.resto.votes = this.parseVotes(data)

    }),
      error => {
        this.spinner_overlay = false;
        console.log('😢 Oh no!', error);
      }
  }

  parseVotes(votes: Vote[]): Vote[] {
    var newVoteListe: Vote[] = [];
    console.log(this.utilisateur)
    console.log(votes)
    if (this.utilisateur) {
      for (var vote of votes) {
        if (vote.present == 1) {

          if (vote.utilisateur.id != this.utilisateur.id) {
            newVoteListe.push(vote)
          } else {
            this.monvote = vote;
            console.log(this.monvote)
          }
        } else {
          if (vote.utilisateur.id != this.utilisateur.id) {
            this.absents.push(vote);
          } else {
            this.monvote = vote;
            console.log(this.monvote)
          }
        }
      }
    } else {
      for (var vote of votes) {
        if (vote.present == 1) {
          newVoteListe.push(vote)
        } else {
          this.absents.push(vote);
        }
      }
    }
    console.log(newVoteListe)
    return newVoteListe;
  }

  getResto(): void {
    this.spinner_overlay = true;
    const id = +this.route.snapshot.paramMap.get('id');

    this.restoService.getFull(id).subscribe(data => {
      this.spinner_overlay = false;
      console.log(data)
      this.resto = data

      const map = L.map("map", {
        zoomControl: false,
        center: L.latLng(47.2161, -1.5492),
        zoom: 18,
        minZoom: 18,
        maxZoom: 18,
        layers: L.tileLayer(
          "http://{s}.tile.openstreetmap.fr/hot/{z}/{x}/{y}.png",
          {
            zIndex: 1,
            attribution: "."
          }
        )
      });
      L.control.zoom({ zoomControl: false }).addTo(map);
      L.control.layers(this.mapService.baseMaps).addTo(map);
      L.control.scale().addTo(map);

      if (this.resto.latitude && this.resto.longitude) {
        L.marker([this.resto.latitude, this.resto.longitude], {
          icon: L.icon({
            iconSize: [25, 41],
            iconAnchor: [13, 41],
            iconUrl: this.resto.rating > 4 ? 'assets/map_marker-red.png' : this.resto.rating > 3 ? 'assets/map_marker-orange1.png' : this.resto.rating > 2 ? 'assets/map_marker-orange2.png' : this.resto.rating > 1 ? 'assets/map_marker-orange3.png' : this.resto.rating > 0 ? 'assets/map_marker-orange4.png' : 'assets/map_marker-grey.png'
          })
        }).addTo(map);
        map.flyTo(L.latLng(this.resto.latitude, this.resto.longitude), 18)
      }

      this.mapService.map = map;

      this.getVotes(this.resto.id);
    }),
      error => {
        this.spinner_overlay = false;
        console.log('😢 Oh no!', error);
      }
  }

  addVoteAbsent(): void {
    this.spinner_overlay = true;
    let currentUtilisateur = JSON.parse(localStorage.getItem('currentUtilisateur'))
    this.voteService.add(this.resto.id, currentUtilisateur.id).subscribe(newVote => {
      console.log(newVote)

      this.monvote = newVote;
      this.monvote.present = 0;

      this.voteService.update(this.resto.id, this.monvote).subscribe(vote => {
        console.log(vote)
        this.monvote = vote
        this.ActualiseMoyenne();
      }),
        error => {
          this.spinner_overlay = false;
          console.log('😢 Oh no!', error);
        }
    }),
      error => {
        this.spinner_overlay = false;
        console.log('😢 Oh no!', error);
      }
  }

  removeVote(): void {
    this.spinner_overlay = true;
    console.log(this.monvote)
    this.monvote.present = 0;

    this.voteService.update(this.resto.id, this.monvote).subscribe(vote => {
      console.log(vote)
      this.resto.votes = this.replaceVote(vote, this.resto.votes)
      this.ActualiseMoyenne();
    }),
      error => {
        this.spinner_overlay = false;
        console.log('😢 Oh no!', error);
      }
  }

  ActualiseMoyenne() {
    this.restoService.getMoyenneForResto(this.resto.id).subscribe(data => {
      console.log(data)
      this.spinner_overlay = false;
      this.resto.rating = data;
      this.modifvote = false;
    }),
      error => {
        this.spinner_overlay = false;
        console.log('😢 Oh no!', error);
      }
  }

  modifyVote(): void {
    this.spinner_overlay = true;

    this.monvote.present = 1;
    console.log(this.monvote)
    this.voteService.update(this.resto.id, this.monvote).subscribe(vote => {
      console.log(vote)
      this.resto.votes = this.replaceVote(vote, this.resto.votes)
      this.ActualiseMoyenne();
    }),
      error => {
        this.spinner_overlay = false;
        console.log('😢 Oh no!', error);
      }
  }

  replaceVote(voteUpdated: Vote, votes: Vote[]): Vote[] {
    for (var vote of votes) {
      if (vote.id == voteUpdated.id) {
        vote = voteUpdated
      }
    }
    return votes;
  }

  addVote(): void {
    this.spinner_overlay = true;
    let currentUtilisateur = JSON.parse(localStorage.getItem('currentUtilisateur'))
    this.voteService.add(this.resto.id, currentUtilisateur.id).subscribe(vote => {
      console.log(vote)
      this.spinner_overlay = false;
      this.monvote = vote;
    }),
      error => {
        this.spinner_overlay = false;
        console.log('😢 Oh no!', error);
      }
  }

  saveResto(): void {
    this.spinner_overlay = true;
    console.log(this.resto)
    this.resto.date = new Date(this.resto.date.getUTCFullYear(), this.resto.date.getUTCMonth(), this.resto.date.getUTCDate(), 12, 15);

    console.log(this.resto)
    this.restoService.add(this.resto).subscribe(data => {
      console.log(data)
      this.spinner_overlay = false;
    }),
      error => {
        this.spinner_overlay = false;
        console.log('😢 Oh no!', error);
      }
    this.modification = false;
  }

  updateResto(): void {
    this.spinner_overlay = true;
    console.log(this.resto)
    this.resto.date = new Date(this.resto.date.getUTCFullYear(), this.resto.date.getUTCMonth(), this.resto.date.getUTCDate(), 12, 15);

    console.log(this.resto)

    this.restoService.update(this.resto).subscribe(data => {
      console.log(data)
      this.spinner_overlay = false;
    }),
      error => {
        this.spinner_overlay = false;
        console.log('😢 Oh no!', error);
      }
    this.modification = false;
  }

  onImageChange(event) {
    this.spinner_overlay = true;
    let image = event.target.files[0];

    this.ng2ImgMax.resizeImage(image, 500, 1000).subscribe(
      result => {
        console.log('image converted');
        this.uploadedImage = new File([result], result.name);
        this.getImagePreview(this.uploadedImage);
        this.spinner_overlay = false;
        console.log(this.uploadedImage)
      },
      error => {
        console.log('😢 Oh no!', error);
        this.spinner_overlay = false;
      }
    );

  }

  getImagePreview(file: File) {
    const reader: FileReader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => {
      console.log(reader.result)
      this.resto.image = reader.result;
    };
  }


  openLink(resto:Resto){
    window.open(resto.website,"_blank");
  }
}
