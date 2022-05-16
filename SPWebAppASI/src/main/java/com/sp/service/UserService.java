package com.sp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.model.Card;
import com.sp.model.User;
import com.sp.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	CardService cService;
	
	@Autowired
	UserRepository uRepository;
	
	public void addUser(User u) {
		User createdUser=uRepository.save(u);
		System.out.println(createdUser);
	}
	
	public User getUser(int userID) {
		Optional<User> uOpt = uRepository.findById(userID);
		if (uOpt.isPresent()) {
			return uOpt.get();
		}else {
			return null;
		}
	}
	
	public List<User> findAllUsers() {
		return (List<User>) uRepository.findAll();
	}
	
	public void initUsers() {
		for (int userID = 0; userID<5; userID++) {
			int numCard = 0;
			List<Card> cardList = new ArrayList<Card>();
			while (numCard < 5) {
				int taille = cService.noOwner().size();
				Card randomCard = cService.noOwner().get((int) (Math.random()*taille-1));
				cardList.add(randomCard);
				numCard++;
			}
			System.out.println(cardList);
			User createdUser=uRepository.save(new User(userID, "pseudo"+userID, "name", "surname", "password", 5000, cardList));
			System.out.println(createdUser);
		}
	}

	public boolean verifUser(String pseudo, String mdp) {
		boolean res = false;
		Optional<User> uOpt = uRepository.findByPseudo(pseudo);
		if (uOpt.isPresent()) {
			if (uOpt.get().getPassword().equals(mdp)) {
				res = true;
			}
		}
		return res;
	}
}



