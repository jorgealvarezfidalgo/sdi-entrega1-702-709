package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;

public interface OffersRepository extends CrudRepository<Offer, Long> {

	Page<Offer> findAllBySeller(Pageable pageable, User seller);

	Page<Offer> findAll(Pageable pageable);

	@Query("SELECT o FROM Offer o WHERE o.seller != ?1")
	Page<Offer> findOthersByUser(Pageable pageable, User buyer);
	
	@Query("SELECT o FROM Offer o WHERE (LOWER(o.title) LIKE LOWER(?1)) and o.seller != ?2")
	Page<Offer> searchOthersByTitle(Pageable pageable, String seachtext, User buyer);

}
