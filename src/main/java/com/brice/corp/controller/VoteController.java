package com.brice.corp.controller;


import java.util.List;

import com.brice.corp.model.Vote;
import com.brice.corp.service.RestoService;
import com.brice.corp.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("https://restoss.herokuapp.com")
@Controller
@RequestMapping("/votes/")
public class VoteController {

	@Autowired
    VoteService voteService;

	@Autowired
	private RestoService restoService;
	////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////// ARTICLE ///////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Vote> addVote(@RequestParam(value = "id", required = false) Integer restoId, @RequestBody Integer utilisateurId) {
		System.out.println("add vote to resto "+restoId + " and utilisateur " + utilisateurId);

		try {
			Vote vote = voteService.add(restoId, utilisateurId);
			return new ResponseEntity<Vote>(vote, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Vote>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<Vote> updateVote(@RequestParam(value = "id", required = false) Integer restoId, @RequestBody Vote vote) {
		System.out.println("update vote "+vote);
		try {
			voteService.update(vote);
			return new ResponseEntity<Vote>(vote, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Vote>(HttpStatus.NOT_FOUND);
		}
	}
	

	@RequestMapping(value = "/liste", method = RequestMethod.GET)
	public ResponseEntity<List<Integer>> getAllVotes() {
		System.out.println("votes");
		try {
			List<Integer> ids = voteService.getAll();
			return new ResponseEntity<List<Integer>>(ids, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<List<Integer>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Vote> get(@RequestParam(value = "id", required = false) Integer id) {
		System.out.println("vote"+id);
		
		try {
			Vote vote = voteService.get(id);
			return new ResponseEntity<Vote>(vote, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Vote>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/byResto", method = RequestMethod.GET)
	public ResponseEntity<List<Vote>> getByRestoId(@RequestParam(value = "id", required = false) Integer id) {
		System.out.println("vote by restoId "+id);
		
		try {
			List<Vote> votes = voteService.getByResto(id);
			return new ResponseEntity<List<Vote>>(votes, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<List<Vote>>(HttpStatus.NOT_FOUND);
		}
	}
}
