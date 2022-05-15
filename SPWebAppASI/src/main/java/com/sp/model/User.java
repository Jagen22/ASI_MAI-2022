package com.sp.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User {
	@Id
	@GeneratedValue
	private Integer userID;
	private String pseudo;
	private String name;
	private String surname;
	private String password;
	private int money;
	
	//Regarder pour nullify a la place de cascade
	@OneToMany(cascade = CascadeType.ALL)
	private List<Card> listCard;
	
	public User() {
	}
	
	public User(Integer id, String pseudo, String name, String surname, String password, int money, List<Card> listCard) {
		super();
		this.userID = id;
		this.pseudo = pseudo;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.money = money;
		this.listCard = listCard;
	}

	public Integer getId() {
		return userID;
	}

	public void setId(Integer id) {
		this.userID = id;
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

}
