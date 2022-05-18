package com.sp.service;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

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
	
	public void saveUser(User u) {
		uRepository.save(u);
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
	
	@PostConstruct
	public void initUsers() {
		cService.initCards(50);
		int numUser = 0;
		while (numUser < 5) {
			User createdUser=new User("pseudo"+numUser, "name", "surname", "password");
			uRepository.save(createdUser);
			for (Card card : cService.get5Card()) {
				card.setOwner(createdUser);
				cService.saveCard(card);
			}
			numUser++;
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

	public void addUser(User u) {
		u.setMoney(5000);
		uRepository.save(u);
		
		for (Card card : cService.get5Card()) {
			card.setOwner(u);
			cService.saveCard(card);
		}
	}
}



