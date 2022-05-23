package com.sp.Card;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "CARD")
public class Card {
	@Id
	@GeneratedValue
	private Integer cardID;
	private String nom;
	private String description;
	private String famille;
	private String affinity;
	private int energy;
	private int hp;
	private int price;
	// Mapping the column of this table
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "User")
    @JoinColumn(referencedColumnName = "userID", name = "userID", nullable = true, unique = false)
	private int  IDowner;
	
	
	public Card() {
	}

	public Card(String nom, String description, String famille, String affinity, int energy, int hp, int price) {
		super();
		this.nom = nom;
		this.description = description;
		this.famille = famille;
		this.affinity = affinity;
		this.energy = energy;
		this.hp = hp;
		this.price = price;
		this.IDowner=-1;
	}

	public Integer getCardID() {
		return cardID;
	}

	public void setCardID(Integer cardID) {
		this.cardID = cardID;
	}

	public int getIDOwner() {
		return IDowner;
	}

	public void setIDOwner(int IDowner) {
		this.IDowner = IDowner;
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

	public String getFamille() {
		return famille;
	}

	public void setFamille(String famille) {
		this.famille = famille;
	}

	public String getAffinity() {
		return affinity;
	}

	public void setAffinity(String affinity) {
		this.affinity = affinity;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	


}
