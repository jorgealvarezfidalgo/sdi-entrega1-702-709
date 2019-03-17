package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Chat;
import com.uniovi.entities.User;

public interface ChatsRepository extends CrudRepository<Chat, Long> {
	
	@Query("SELECT c FROM Chat c where c.creator = ?1 or c.offer.seller = ?1")
	Page<Chat> findByUser(Pageable pageable, User user);

}
