package com.brice.corp.service;

import java.util.List;

import com.brice.corp.dao.UtilisateurDao;
import com.brice.corp.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("utilisateurService")
@Transactional
public class UtilisateurService{

	@Autowired
	private UtilisateurDao dao;



	
	public Utilisateur findByName(String sso) {
		Utilisateur utilisateur = dao.findBySso(sso);
			
		return utilisateur;
	}
	
	
	public Utilisateur authenticate(Object object){
		System.out.println(object);
		
		return new Utilisateur();
	}
	

	public boolean isUtilisateurExist(Utilisateur utilisateur) {
		return findByName(utilisateur.getLastName())!=null;
	}
	
	public void deleteAllUtilisateurs(){
		dao.findAllUtilisateurs().clear();
	}
	
	public Utilisateur findById(int id) {
		System.out.println("find utilisateur by id");
		return dao.findById(id);
	}

	public Utilisateur findBySso(String sso) {
		Utilisateur utilisateur = dao.findBySso(sso);
		return utilisateur;
	}

	public void saveUtilisateur(Utilisateur utilisateur) {
		dao.save(utilisateur);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateUtilisateur(Utilisateur utilisateur) {
		Utilisateur entity = dao.findById(utilisateur.getId());
		if(entity!=null){
			entity.setSsoId(utilisateur.getSsoId());
			entity.setPassword(utilisateur.getPassword());
			entity.setFirstName(utilisateur.getFirstName());
			entity.setLastName(utilisateur.getLastName());
			entity.setEmail(utilisateur.getEmail());
			entity.setUtilisateurProfiles(utilisateur.getUtilisateurProfiles());
		}
	}

	
	public void deleteUtilisateurBySSO(String sso) {
		dao.deleteBySSO(sso);
	}

	
	
	
	public List<Integer> findAllUtilisateursActifs() {
		List<Integer> utilisateurs= dao.findAllUtilisateursActifs();
		return utilisateurs;
	}
	
	public List<Integer> findAllUtilisateurs() {
		List<Integer> utilisateurs= dao.findAllUtilisateurs();
		return utilisateurs;
	}

	public boolean isUtilisateurSSOUnique(Integer id, String sso) {
		Utilisateur utilisateur = findBySso(sso);
		return ( utilisateur == null || ((id != null) && (utilisateur.getId() == id)));
	}
	
}
