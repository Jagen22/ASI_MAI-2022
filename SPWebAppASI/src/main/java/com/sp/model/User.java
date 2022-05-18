package com.sp.model;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User {
	@Id
	@GeneratedValue
	private Integer userID;
	private String pseudo;
	private String name;
	private String surname;
	private String password;
	private int money;
	
	@OneToMany(mappedBy="owner")
	private List<Card> listCard;
	
	public User() {
	}
	
	public User(String pseudo, String name, String surname, String password) {
		super();
		this.pseudo = pseudo;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.money = 5000;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public List<Card> getListCard() {
		return listCard;
	}

	public void setListCard(List<Card> listCard) {
		this.listCard = listCard;
	}
	
	public void addCard(Card card) {
		this.listCard.add(card);
	}
	
	public void removeCard(Card card) {
		this.listCard.remove(card);
	}

}
