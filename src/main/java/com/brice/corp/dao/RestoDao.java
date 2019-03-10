package com.brice.corp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.brice.corp.model.Resto;
import com.brice.corp.model.Vote;

@Repository("restoDao")
public class RestoDao extends AbstractDao<Integer, Resto> {

	public void add(Resto resto) {
		persist(resto);
	}

	public void update(Resto resto) {
		update(resto);
	}
	
	public List<Integer> getAll() {
		@SuppressWarnings("unchecked")
		List<Integer> liste = getEntityManager()
		.createQuery("SELECT a.id FROM Resto a ")
		.getResultList();
		return liste;
	}
	
	@SuppressWarnings("unchecked")
	public Resto get(Integer id) {
		List<Resto> restos = getEntityManager()
				.createQuery("SELECT a FROM Resto a WHERE (a.id = :id)")
				.setParameter("id", id)
				.getResultList();
		return restos.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<Integer> getByCategorie(Integer id) {
		List<Integer> restos = getEntityManager()
				.createQuery("SELECT a.id FROM Resto a WHERE (a.categorie.id = :id)")
				.setParameter("id", id)
				.getResultList();
		return restos;
	}

	@SuppressWarnings("unchecked")
	public List<Vote> getVotes(Integer id) {
		System.out.println("lookfor Votes of resto "+id);
		List<Vote> votes = getEntityManager()
				.createQuery("SELECT a.votes FROM Resto a WHERE (a.id = :id)")
				.setParameter("id", id)
				.getResultList();
		return votes;
	}
	
	
	public void delete(Integer id) {
		delete(this.get(id));
	}

	
}
