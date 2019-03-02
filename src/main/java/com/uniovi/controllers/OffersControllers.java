package com.uniovi.controllers;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.services.OffersService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.OfferFormValidator;

@Controller
public class OffersControllers {

	@Autowired
	private OffersService offersService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private OfferFormValidator offerFormValidator;

	@RequestMapping("/offer/listown")
	public String getList(Model model, Pageable pageable, Principal principal) {
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		Page<Offer> offers = offersService.getOffersForUser(pageable, user);

		model.addAttribute("offerList", offers.getContent());
		model.addAttribute("page", offers);
		return "offer/listown";
	}

	@RequestMapping(value = "/offer/add", method = RequestMethod.POST)
	public String setOffer(@Validated @ModelAttribute Offer offer, BindingResult result, Model model) {
		offerFormValidator.validate(offer, result);
		if (result.hasErrors()) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date today = new Date(System.currentTimeMillis()); 
			String todayAsString = df.format(today);
			model.addAttribute("today", todayAsString);
			model.addAttribute("usersList", usersService.getUsers());
			return "offer/add";
		}
		offersService.addOffer(offer);
		return "redirect:/offer/listown";
	}

	@RequestMapping("/offer/delete/{id}")
	public String deleteOffer(@PathVariable Long id) {
		offersService.deleteOffer(id);
		return "redirect:/offer/listown";
	}

	@RequestMapping(value = "/offer/add")
	public String getOffer(Model model) {
		model.addAttribute("offer", new Offer());
		model.addAttribute("usersList", usersService.getUsers());
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date(System.currentTimeMillis()); 
		String todayAsString = df.format(today);
		model.addAttribute("today", todayAsString);
		return "offer/add";
	}

	@RequestMapping("/offer/list/update")
	public String updateList(Model model, Pageable pageable, Principal principal) {
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		Page<Offer> offers = offersService.getOffersForUser(pageable, user);
		model.addAttribute("offerList", offers.getContent());
		return "offer/list :: tableOffers";
	}

}
