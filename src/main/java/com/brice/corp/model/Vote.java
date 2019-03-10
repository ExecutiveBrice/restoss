package com.brice.corp.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="vote")
public class Vote {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="commentaire")
	private String commentaire;
	
	@Column(name="rating")
	private Integer rating;
	
	
	@Column(name="rating2")
	private Integer rating2;



	@Column(name="rating3")
	private Integer rating3;
	
	@Column(name="present")
	private Integer present;
	
	@JsonIgnore
	@JoinColumn(name = "resto", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Resto resto;
	
	@JoinColumn(name = "utilisateur", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Utilisateur utilisateur;

	public Vote() {
		super();
	}
	
	public Vote(Utilisateur utilisateur, Resto resto) {
		this();
		setUtilisateur(utilisateur);
		setResto(resto);
	}
	

	public Integer getPresent() {
		return present;
	}



	public void setPresent(Integer present) {
		this.present = present;
	}





	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}



	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}
    public Integer getRating2() {
        return rating2;
    }

    public void setRating2(Integer rating2) {
        this.rating2 = rating2;
    }
    public Integer getRating3() {
        return rating3;
    }

    public void setRating3(Integer rating3) {
        this.rating3 = rating3;
    }
	public Resto getResto() {
		return resto;
	}

	public void setResto(Resto resto) {
		this.resto = resto;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vote other = (Vote) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vote [getId()=" + getId() + ", getCommentaire()=" + getCommentaire() + ", getRating()=" + getRating()
				+ ", getResto()=" + getResto() + ", getUtilisateur()=" + getUtilisateur() + ", hashCode()=" + hashCode() + "]";
	}
	
	
	

}
