package com.brice.corp.model;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="resto")
public class Resto {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="nom")
	private String nom;

	@Column(name="description")
	private String description;

	@Column(name="menu")
	private String menu;
	
	@Column(name="entreplat")
	private String entreplat;
	
	@Column(name="plat")
	private String plat;
	
	@Column(name="adresse")
	private String adresse;
	
	@Column(name="tel")
	private String tel;
	
	@Column(name="rating")
	private Double rating;


	@Column(name="website")
	private String website;

	@Column(name="image")
	private String image;

	@Column(name="date")
	private Date date;
	
	@Column(name="latitude")
	private Double latitude;
	
	@Column(name="longitude")
	private Double longitude;
	
	@Column(name="citation")
	private String citation;
	
	@JsonIgnore
	@JoinColumn(name = "resto", referencedColumnName = "id")
	@OneToMany(fetch = FetchType.EAGER)
	private Set<Vote> votes = new HashSet<Vote>();

	public Resto(){
		super();
	}
	
	public String getCitation() {
		return citation;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public void setCitation(String citation) {
		this.citation = citation;
	}

	public String getEntreplat() {
		return entreplat;
	}

	public void setEntreplat(String entreplat) {
		this.entreplat = entreplat;
	}

	public String getPlat() {
		return plat;
	}

	public void setPlat(String plat) {
		this.plat = plat;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Set<Vote> getVotes() {
		return votes;
	}

	public void setVotes(Set<Vote> votes) {
		this.votes = votes;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Resto other = (Resto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Resto [getId()=" + getId() + ", getDate()=" + getDate() + ", getNom()=" + getNom()
				+ ", getDescription()=" + getDescription() + ", getRating()=" + getRating() + ", getImage()="
				+ getImage() + ", getVotes()=" + getVotes() + ", getMenu()=" + getMenu() + ", getAdresse()="
				+ getAdresse() + ", getTel()=" + getTel() + ", hashCode()=" + hashCode() + "]";
	}

	
	
}
