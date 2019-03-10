package com.brice.corp.model;


import java.util.Date;

public class RestoLite {
	
	private Integer id;
	
	private String nom;

	private Double rating;

	private Date date;
	
	private Integer monId;
	
	private Double latitude;
	
	private Double longitude;
	
	public RestoLite (){
	super();	
	}
	
	public RestoLite (Resto resto){
		this();	
		setId(resto.getId());
		setNom(resto.getNom());
		setRating(resto.getRating());
		setDate(resto.getDate());
		setLongitude(resto.getLongitude());
		setLatitude(resto.getLatitude());
		}
	

	public Integer getMonId() {
		return monId;
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

	public void setMonId(Integer monId) {
		this.monId = monId;
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

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
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
		RestoLite other = (RestoLite) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RestoLite [getMonId()=" + getMonId() + ", getId()=" + getId() + ", getDate()=" + getDate()
				+ ", getNom()=" + getNom() + ", getRating()=" + getRating() + ", hashCode()=" + hashCode() + "]";
	}
	
}
