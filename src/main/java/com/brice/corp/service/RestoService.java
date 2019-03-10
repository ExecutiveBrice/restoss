package com.brice.corp.service;


import java.util.List;

import com.brice.corp.dao.RestoDao;
import com.brice.corp.model.Resto;
import com.brice.corp.model.RestoLite;
import com.brice.corp.model.Utilisateur;
import com.brice.corp.model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("restoService")
@Transactional
public class RestoService{

	@Autowired
	private RestoDao dao;

	@Autowired
	private VoteService voteService;
	
	@Autowired
	private UtilisateurService utilisateurService;
	
	
	public Resto getFull(Integer id) {
		Resto item = dao.get(id);
		return item;
	}
	
	public RestoLite get(Integer userId, Integer id) {
		Resto item = dao.get(id);
		RestoLite restoLite = new RestoLite(item);
		List<Vote> votes = voteService.getByResto(id);

		for (Vote vote : votes) {
			if(userId!=null && vote.getUtilisateur().getId() == userId && vote.getPresent() == 1) {
				restoLite.setMonId(userId);
			}
		}
		return restoLite;
	}
	
	public List<Integer> getByCategorie(Integer id) {
		List<Integer> items = dao.getByCategorie(id);
		return items;
	}
	
	public double calculMoyenne(Integer restoId) {
		System.out.println("calcul de la moyenne");

		List<Vote> votes = dao.getVotes(restoId);
		double moyenne = 0;
		double nbVotes = 0;
		for (Vote vote : votes) {
			if(vote.getPresent() == 1) {
			if(vote.getRating() != null) {
			moyenne = moyenne + vote.getRating();
			nbVotes++;
			}
			if(vote.getRating2() != null) {
			moyenne = moyenne + vote.getRating2();
			nbVotes++;
			}
				if(vote.getRating3() != null) {
					moyenne = moyenne + vote.getRating3();
					nbVotes++;
				}
			}
		}
		if(nbVotes != 0) {
			moyenne = moyenne / nbVotes;
			moyenne = Math.floor(moyenne * 100) / 100;
		}
		
		
		Resto resto = getFull(restoId);
		resto.setRating(moyenne);
		return moyenne;
	}
	
	public void update(Resto resto) {
		Resto updatedResto = getFull(resto.getId());
		updatedResto.setDate(resto.getDate());
		updatedResto.setDescription(resto.getDescription());
		updatedResto.setImage(resto.getImage());
		updatedResto.setMenu(resto.getMenu());
		updatedResto.setNom(resto.getNom());
		updatedResto.setAdresse(resto.getAdresse());
		updatedResto.setTel(resto.getTel());
		updatedResto.setLatitude(resto.getLatitude());
		updatedResto.setLongitude(resto.getLongitude());
		updatedResto.setWebsite(resto.getWebsite());
        updatedResto.setCitation(resto.getCitation());
	}
	
	
	public void add(Resto resto) {
		dao.add(resto);
	}	

	public List<Integer> getAll() {
		return dao.getAll();
	}	

	public void delete(Integer id) {
		dao.delete(id);;
	}
	
}
