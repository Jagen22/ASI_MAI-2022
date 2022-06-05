package com.sp.user;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.CardPriceDTO;
import com.sp.CardWSClient;

@Service
public class UserService {
	
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
		CardWSClient.initCards(50);
		int numUser = 0;
		while (numUser < 5) {
			User createdUser=new User("User"+numUser, "name", "surname", "password");
			uRepository.save(createdUser);
			for (CardPriceDTO card : CardWSClient.userCards()) {
				card.setUserID(createdUser.getUserID());
				CardWSClient.saveCard(card);
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

	public boolean addUser(User u) {		
		boolean res = false;
		Optional<User> uOpt = uRepository.findByPseudo(u.getPseudo());
		if (!uOpt.isPresent()) {
			u.setMoney(2000);
			uRepository.save(u);
			for (CardPriceDTO card : CardWSClient.userCards()) {
				card.setUserID(u.getUserID());
				CardWSClient.saveCard(card);
			}
			res = true;
		}
		return res;
	}
}
