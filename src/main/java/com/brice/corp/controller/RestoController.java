package com.brice.corp.controller;


import java.util.List;

import com.brice.corp.model.Resto;
import com.brice.corp.model.RestoLite;
import com.brice.corp.service.RestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("https://restoss.herokuapp.com")
@Controller
@RequestMapping("/restos")
public class RestoController {

	@Autowired
    RestoService restoService;

	////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////// ARTICLE ///////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Resto> addResto(@RequestBody Resto resto) {
		System.out.println("resto");
		System.out.println(resto.toString());
		try {
			restoService.add(resto);
			return new ResponseEntity<Resto>(resto, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Resto>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<Resto> updateResto(@RequestBody Resto resto) {
		System.out.println("update resto " + resto);
		try {
			restoService.update(resto);
			return new ResponseEntity<Resto>(resto, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Resto>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/liste", method = RequestMethod.GET)
	public ResponseEntity<List<Integer>> getAllRestos() {
		System.out.println("restos");
		try {
			List<Integer> ids = restoService.getAll();
			return new ResponseEntity<List<Integer>>(ids, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<List<Integer>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/restoFull", method = RequestMethod.GET)
	public ResponseEntity<Resto> getFull(@RequestParam(value = "id", required = false) Integer id) {
		System.out.println("resto"+id);
		try {
			Resto resto = restoService.getFull(id);
			return new ResponseEntity<Resto>(resto, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Resto>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<RestoLite> getLite(@RequestParam(value = "userId", required = false) Integer userId, @RequestParam(value = "id", required = false) Integer id) {
		System.out.println("resto id : "+id);
		System.out.println("userId userId : "+userId);
		try {
			RestoLite restoLite = restoService.get(userId, id);
			return new ResponseEntity<RestoLite>(restoLite, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<RestoLite>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/moyenne", method = RequestMethod.GET)
	public ResponseEntity<Double> getmoyenne(@RequestParam(value = "id", required = false) Integer restoId) {
		System.out.println("resto "+restoId);
		try {
			Double moyenneRating = restoService.calculMoyenne(restoId);
			return new ResponseEntity<Double>(moyenneRating, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Double>(HttpStatus.NOT_FOUND);
		}
	}

}