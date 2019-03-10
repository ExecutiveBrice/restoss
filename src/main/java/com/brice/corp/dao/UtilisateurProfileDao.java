package com.brice.corp.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.brice.corp.model.UtilisateurProfile;



@Repository("utilisateurProfileDao")
public class UtilisateurProfileDao extends AbstractDao<Integer, UtilisateurProfile>{

	public UtilisateurProfile findById(int id) {
		return getByKey(id);
	}

	public UtilisateurProfile findByType(String type) {
		System.out.println("type: "+type);
		try{
			UtilisateurProfile utilisateurProfile = (UtilisateurProfile) getEntityManager()
					.createQuery("SELECT p FROM UtilisateurProfile p WHERE p.type LIKE :type")
					.setParameter("type", type)
					.getSingleResult();
			return utilisateurProfile; 
		}catch(NoResultException ex){
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<UtilisateurProfile> findAll(){
		List<UtilisateurProfile> utilisateurProfiles = getEntityManager()
				.createQuery("SELECT p FROM UtilisateurProfile p  ORDER BY p.type ASC")
				.getResultList();
		System.out.println("utilisateurProfiles: "+utilisateurProfiles);
		return utilisateurProfiles;
	}
	
}
