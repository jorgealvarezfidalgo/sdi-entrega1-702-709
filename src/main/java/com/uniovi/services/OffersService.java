package com.uniovi.services;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.repositories.OffersRepository;

@Service
public class OffersService {

	@Autowired
	private OffersRepository offersRepository;

	public Page<Offer> getOffers(Pageable pageable) {
		Page<Offer> offers = offersRepository.findAll(pageable);
		return offers;
	}

	public void addOffer(Offer Offer) {
		offersRepository.save(Offer);
	}

	public void deleteOffer(Long id) {
		offersRepository.deleteById(id);
	}

	public Page<Offer> getOffersForUser(Pageable pageable, User seller) {
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		if (seller.getRole().equals("ROLE_USER")) {
			offers = offersRepository.findAllBySeller(pageable, seller);
		}
		return offers;
	}

	public Page<Offer> getOtherUsersOffers(Pageable pageable, User buyer) {
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		if (buyer.getRole().equals("ROLE_USER")) {
			offers = offersRepository.findOthersByUser(pageable, buyer);
		}
		return offers;
	}

	public Offer findById(Long id) {
		Offer offer = offersRepository.findById(id).get();
		return offer;
	}

	public Page<Offer> searchOthersOffersByTitle(Pageable pageable, String searchText, User buyer) {
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		searchText = "%" + searchText + "%";
		if (buyer.getRole().equals("ROLE_USER")) {
			offers = offersRepository.searchOthersByTitle(pageable, searchText, buyer);
		}
		return offers;
	}

	public Page<Offer> getOffersBought(Pageable pageable, User buyer) {
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		if (buyer.getRole().equals("ROLE_USER")) {
			offers = offersRepository.findBoughtByUser(pageable, buyer);
		}
		return offers;
	}
	
	public Page<Offer> getHighlightedOffers(Pageable pageable, User buyer) {
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		offers = offersRepository.findAllHighlighted(pageable, buyer);

		return offers;
	}
	
	public void setOfferHighlight(boolean highlight, Long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		Offer offer = offersRepository.findById(id).get();
		if (offer.getSeller().getEmail().equals(email)) {
			offersRepository.updateHighlight(highlight, id);
		}
	}

}
