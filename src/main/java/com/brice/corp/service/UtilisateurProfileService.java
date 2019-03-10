package com.brice.corp.service;

import java.util.List;

import com.brice.corp.dao.UtilisateurProfileDao;
import com.brice.corp.model.UtilisateurProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("utilisateurProfileService")
@Transactional
public class UtilisateurProfileService {
	
	@Autowired
    UtilisateurProfileDao dao;
	
	public UtilisateurProfile findById(int id) {
		return dao.findById(id);
	}

	public UtilisateurProfile findByType(String type){
		return dao.findByType(type);
	}

	public List<UtilisateurProfile> findAll() {
		return dao.findAll();
	}
}
