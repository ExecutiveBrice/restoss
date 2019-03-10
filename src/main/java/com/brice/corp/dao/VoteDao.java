package com.brice.corp.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.brice.corp.model.Vote;



@Repository("voteDao")
public class VoteDao extends AbstractDao<Integer, Vote> {

	public void add(Vote item) {
		persist(item);
	}

	public void update(Vote item) {
		System.out.println("update");
		update(item);
	}
	
	public List<Integer> getAll() {
		@SuppressWarnings("unchecked")
		List<Integer> liste = getEntityManager()
		.createQuery("SELECT a.id FROM Vote a ")
		.getResultList();
		return liste;
	}
	
	@SuppressWarnings("unchecked")
	public Vote get(Integer id) {
		List<Vote> items = getEntityManager()
				.createQuery("SELECT a FROM Vote a WHERE (a.id = :id)")
				.setParameter("id", id)
				.getResultList();
		return items.get(0);
	}


	
	@SuppressWarnings("unchecked")
	public List<Vote> getByResto(Integer restoId) {
		List<Vote> items = getEntityManager()
				.createQuery("SELECT a FROM Vote a WHERE (a.resto.id = :restoId)")
				.setParameter("restoId", restoId)
				.getResultList();
		return items;
	}

	
	public void delete(Integer id) {
		delete(this.get(id));
	}

	
}
