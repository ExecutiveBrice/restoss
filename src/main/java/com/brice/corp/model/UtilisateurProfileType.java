package com.brice.corp.model;

public enum UtilisateurProfileType {
	UTILISATEUR("UTILISATEUR"),
	DBA("DBA"),
	ADMIN("ADMIN");
	
	String utilisateurProfileType;

	private UtilisateurProfileType(String utilisateurProfileType){
		this.utilisateurProfileType = utilisateurProfileType;
	}
	
	public String getUtilisateurProfileType(){
		return utilisateurProfileType;
	}
	
}
