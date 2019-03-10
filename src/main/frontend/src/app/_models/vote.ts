import { Utilisateur } from "./utilisateur";
import { Resto } from "./resto";

export class Vote {
  id: number;
  commentaire: string;
  rating: Number;
  rating2: Number;
  utilisateur: Utilisateur;
  present: Number;

}
