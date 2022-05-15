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
	
	public void addCard(Card c) {
		Card createdCard=cRepository.save(c);
		System.out.println(createdCard);
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
		boolean res = true;
		int cardID = 1;
		List<Card> Lcard = new ArrayList<Card>();
		while(res) {
			Optional<Card> cOpt = cRepository.findById(cardID);
			if (cOpt.isPresent()) {
				Lcard.add(cOpt.get());
			}else {
				res = false;
			}
			cardID++;
		}
		
		return Lcard;
	}
	
	public void initCards() {
		for (int cardID = 0; cardID<30; cardID++) {
			Card createdCard=cRepository.save(new Card(cardID, "name", "description", "famille", "affinity", 10+cardID*10, 30, 500));
			System.out.println(createdCard);
		}
	}

}