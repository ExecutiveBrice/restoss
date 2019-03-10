package com.brice.corp.service;


import java.util.List;

import com.brice.corp.dao.VoteDao;
import com.brice.corp.model.Resto;
import com.brice.corp.model.Utilisateur;
import com.brice.corp.model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("voteService")
@Transactional
public class VoteService{

	@Autowired
	private VoteDao dao;

	@Autowired
	private UtilisateurService utilisateurService;

	@Autowired
	private RestoService restoService;


	public Vote get(Integer id) {
		Vote item = dao.get(id);
		return item;
	}
	
	public List<Vote> getByResto(Integer restoId) {
		List<Vote> items = dao.getByResto(restoId);
		return items;
	}
	

	
	public void update(Vote vote) {
		System.out.println("update vote");
		Vote updatedVote = get(vote.getId());
		updatedVote.setRating(vote.getRating());
		updatedVote.setRating2(vote.getRating2());
		updatedVote.setRating3(vote.getRating3());
		updatedVote.setPresent(vote.getPresent());
		updatedVote.setCommentaire(vote.getCommentaire());
	}
	
	

	
	public Vote add(Integer restoId, Integer utilisateurId) {
		Utilisateur utilisateur = utilisateurService.findById(utilisateurId);
		Resto resto = restoService.getFull(restoId);
		Vote vote = new Vote(utilisateur, resto);
		vote.setPresent(1);
		dao.add(vote);
		return vote;
	}	

	public List<Integer> getAll() {
		return dao.getAll();
	}	

	public void delete(Integer id) {
		dao.delete(id);
	}
	
}
