package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.User;

public interface UsersRepository extends CrudRepository<User, Long> {

	User findByEmail(String email);

	String findByPassword(String password);
	
	@Query("SELECT r FROM User r WHERE role!='ROLE_ADMIN'")
	Page<User> findAll(Pageable pageable);


}
