package com.sp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.model.User;
import com.sp.repository.UserRepository;

@Service
public class UserService {
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
		boolean res = true;
		int userID = 1;
		List<User> Luser = new ArrayList<User>();
		while(res) {
			Optional<User> uOpt = uRepository.findById(userID);
			if (uOpt.isPresent()) {
				Luser.add(uOpt.get());
			}else {
				res = false;
			}
			userID++;
		}
		
		return Luser;
	}
	
	public void initUsers() {
		for (int userID = 0; userID<10; userID++) {
			User createdUser=uRepository.save(new User(userID, "pseudo"+userID, "name", "surname", "password", 5000, null));
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



