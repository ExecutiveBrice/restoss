package com.brice.corp.controller;
 
import java.util.List;

import com.brice.corp.model.Utilisateur;
import com.brice.corp.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/utilisateur")
public class UtilisateurRestController {
 
    @Autowired
    UtilisateurService utilisateurService;  //Service which will do all data retrieval/manipulation work

   //-------------------Retrieve All Utilisateurs Actifs--------------------------------------------------------
   
   @RequestMapping(value = "/actifs", method = RequestMethod.GET, produces = "application/json")
   public ResponseEntity<List<Integer>> listAllUtilisateursAtcifs() {
		System.out.println("utilisateursActifs");
		try {
			List<Integer> utilisateurs = utilisateurService.findAllUtilisateursActifs();
			if (utilisateurs != null) {
				return new ResponseEntity<List<Integer>>(utilisateurs, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<Integer>>(utilisateurs, HttpStatus.NO_CONTENT);
			}
		} catch (final Exception e) {
			System.out.println("Exception :" + e);
			return new ResponseEntity<List<Integer>>(HttpStatus.CONFLICT);
		}
   }
   
   
    //-------------------Retrieve All Utilisateurs--------------------------------------------------------
     
    @RequestMapping(value = "/liste", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Integer>> listAllUtilisateurs() {
		System.out.println("utilisateurs");
		try {
			List<Integer> utilisateurs = utilisateurService.findAllUtilisateurs();
			if (utilisateurs != null) {
				return new ResponseEntity<List<Integer>>(utilisateurs, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<Integer>>(utilisateurs, HttpStatus.NO_CONTENT);
			}
		} catch (final Exception e) {
			System.out.println("Exception :" + e);
			return new ResponseEntity<List<Integer>>(HttpStatus.CONFLICT);
		}
    }
 
 
    //-------------------Retrieve Single Utilisateur--------------------------------------------------------
     
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Utilisateur> getUtilisateur(@RequestParam(value = "id", required = false) Integer id) {
        System.out.println("Fetching Utilisateur with id " + id);
        Utilisateur utilisateur = utilisateurService.findById(id);
        if (utilisateur == null) {
            System.out.println("Utilisateur with id " + id + " not found");
            return new ResponseEntity<Utilisateur>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Utilisateur>(utilisateur, HttpStatus.OK);
    }
 


 
}