package com.sp;

import java.util.List;

public class UserCardDTO {
	
	private Integer userID;
	private int money;
	private List<Integer> ListIDCard;
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public List<Integer> getListIDCard() {
		return ListIDCard;
	}
	public void setListIDCard(List<Integer> listIDCard) {
		ListIDCard = listIDCard;
	}
	public void addCard(int idCard) {
		this.ListIDCard.add(idCard);
	}
	public void removeCard(int idCard) {
		this.ListIDCard.remove(idCard);
	}
	
}
