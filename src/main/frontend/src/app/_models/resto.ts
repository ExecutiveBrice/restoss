import { Vote } from "./vote";

export class Resto {
  id: number;
  nom: string;
  date: Date;
  description: string;
  adresse: string;
  tel: string;
  rating: Number;
  rating2: Number;
  rating3: Number;
  votes : Vote[] = [];
  monId : number;
  website : string;
  image: string | ArrayBuffer;
  menu : string;
  entreplat : string;
  plat : string;
  latitude: number;
  longitude:number;
  citation:string;

}
