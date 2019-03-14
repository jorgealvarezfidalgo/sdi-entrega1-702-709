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
	
	@Modifying
	@Transactional
	@Query("UPDATE Offer SET destacada = ?1 WHERE id = ?2")
	void updateHighlight(Boolean highlight, Long id);

	@Query("SELECT o FROM Offer o WHERE o.seller = ?1 ORDER BY o.title")
	Page<Offer> findAllBySeller(Pageable pageable, User seller);

	Page<Offer> findAll(Pageable pageable);

	@Query("SELECT o FROM Offer o WHERE o.seller != ?1 ORDER BY o.title")
	Page<Offer> findOthersByUser(Pageable pageable, User buyer);
	
	@Query("SELECT o FROM Offer o WHERE (LOWER(o.title) LIKE LOWER(?1)) and o.seller != ?2 ORDER BY o.title")
	Page<Offer> searchOthersByTitle(Pageable pageable, String seachtext, User buyer);

	@Query("SELECT o FROM Offer o WHERE o.buyer = ?1")
	Page<Offer> findBoughtByUser(Pageable pageable, User buyer);
	
	@Query("SELECT o FROM Offer o WHERE o.destacada = true AND o.seller != ?1")
	Page<Offer> findAllHighlighted(Pageable pageable, User buyer);

}
