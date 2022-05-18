package com.sp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.model.Card;
import com.sp.repository.CardRepository;

@Service
public class CardService {
	@Autowired
	CardRepository cRepository;
	
	public void saveCard(Card c) {
		cRepository.save(c);
	}
	
	public Card getCard(int cardID) {
		Optional<Card> cOpt = cRepository.findById(cardID);
		if (cOpt.isPresent()) {
			return cOpt.get();
		}else {
			return null;
		}
	}
	
	public List<Card> findAllCards() {
		return (List<Card>) cRepository.findAll();
	}
	
	public List<Card> noOwner(){
		List<Card> noOwnerList = new ArrayList<Card>();
		for (Card card : this.findAllCards()) {
			if (card.getOwner()==null) {
				noOwnerList.add(card);
			}
		}
		return noOwnerList;
	}
	
	public List<Card> get5Card(){
		int numCard = 0;
		List<Card> cardList = new ArrayList<Card>();
		List<Card> cardNoOwnerList = this.noOwner();
		int taille = cardNoOwnerList.size();
		if (taille <= 10) {
			initCards(5);
		}
		while (numCard < 5) {
			int rand = (int) (Math.random()*(taille-1-numCard));
			Card randomCard = cardNoOwnerList.get(rand);
			cardList.add(randomCard);
			cardNoOwnerList.remove(rand);
			numCard++;
		}
		return cardList;
	}
	
	public void initCards(int nombre) {
		int numCard = 0;
		while (numCard < nombre) {
			
			cRepository.save(this.randomStats());
			numCard++;
		}
	}
	
	public Card randomStats() {
		int energy = 10 * (int) (Math.random()*5+1);
		int hp = 50 + 10 * (int) (Math.random()*20);
		Card card = new Card("name", "description", "famille", "affinity",energy,hp,(hp*energy)/20);
		return card;
	}

}