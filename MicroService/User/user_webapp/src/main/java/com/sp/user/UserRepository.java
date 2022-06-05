package com.sp.user;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

	public Optional<User> findByPseudo(String pseudo);
}