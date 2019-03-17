package com.uniovi.controllers;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/offer/listown")
	public String getList(HttpSession session, Model model, Pageable pageable, Principal principal) {
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		Page<Offer> offers = offersService.getOffersForUser(pageable, user);
		
		log.info("Listing own offers for {}", email);
		session.setAttribute("saldo", user.getSaldo());
		model.addAttribute("offerList", offers.getContent());
		model.addAttribute("page", offers);
		return "offer/listown";
	}

	@RequestMapping(value = "/offer/add", method = RequestMethod.POST)
	public String setOffer(@Validated @ModelAttribute Offer offer, HttpSession session, BindingResult result, Model model,
			Principal principal) {
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		offer.setSeller(user);
		offerFormValidator.validate(offer, result);
		if (result.hasErrors()) {
			log.info("Error adding offer by {}", email);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date today = new Date(System.currentTimeMillis());
			String todayAsString = df.format(today);
			model.addAttribute("currentDate", todayAsString);
			model.addAttribute("usersList", usersService.getUsers());
			return "offer/add";
		}	
		log.info("{} creates offer {}", email, offer.getTitle());
		if(offer.isDestacada()) {
			log.info("Offer {} has been created as highlighted", offer.getTitle());
			user.setSaldo(user.getSaldo() - 20);
			usersService.updateUser(user);
			session.setAttribute("saldo", user.getSaldo());
		}
		offer.setDate(new Date(System.currentTimeMillis()));
		offersService.addOffer(offer);
		return "redirect:/offer/listown";
	}

	@RequestMapping("/offer/delete/{id}")
	public String deleteOffer(@PathVariable Long id, Principal principal) {
		String email = principal.getName();
		offersService.deleteOffer(id);
		log.info("{} deletes offer {}", email, id);
		return "redirect:/offer/listown";
	}

	@RequestMapping(value = "/offer/add")
	public String getOffer(Model model, Principal principal, HttpSession session) {
		model.addAttribute("offer", new Offer());
		model.addAttribute("usersList", usersService.getUsers());
		
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		session.setAttribute("saldo", user.getSaldo());

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date(System.currentTimeMillis());
		String todayAsString = df.format(today);
		model.addAttribute("currentDate", todayAsString);
		log.info("Loading add form for {}", email);
		return "offer/add";
	}

	@RequestMapping("/offer/list/update")
	public String updateList(Model model, Pageable pageable, Principal principal) {
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		Page<Offer> offers = offersService.getOffersForUser(pageable, user);
		model.addAttribute("offerList", offers.getContent());
		log.info("Updating offers table for {}", email);
		return "offer/list :: tableOffers";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
	}

	@RequestMapping("/offer/listothers")
	public String getListOthers(HttpSession session, Model model, Pageable pageable, Principal principal,
			@RequestParam(value = "", required = false) String searchText) {
		String email = principal.getName();
		User buyer = usersService.getUserByEmail(email);
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>()); // offersService.getOtherUsersOffers(pageable,
																			// user);
		if (searchText != null && !searchText.isEmpty()) {
			offers = offersService.searchOthersOffersByTitle(pageable, searchText, buyer);
		} else {
			offers = offersService.getOtherUsersOffers(pageable, buyer);
		}
		log.info("{} looking for offers.", email);
		session.setAttribute("saldo", buyer.getSaldo());
		model.addAttribute("offerList", offers.getContent());
		model.addAttribute("page", offers);
		return "offer/listothers";
	}

	@RequestMapping("/offer/listothers/update")
	public String updateListOthers(Model model, Pageable pageable, Principal principal) {
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		Page<Offer> offers = offersService.getOtherUsersOffers(pageable, user);
		log.info("Updating changes on others' offers list for {}", email);
		model.addAttribute("offerList", offers.getContent());
		return "offer/listothers :: tableOffers";
	}
	
	@RequestMapping("/home/update")
	public String updateHome(HttpSession session, Model model, Pageable pageable, Principal principal) {
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		Page<Offer> offers = offersService.getHighlightedOffers(pageable, user);
		model.addAttribute("offerList", offers.getContent());
		log.info("Updating highlighted offers at {} home.", email);
		return "home :: tableOffers";
	}

	@RequestMapping(value = "/offer/{id}/buy", method = RequestMethod.GET)
	public String buyOffer(Model model, Principal principal, @PathVariable Long id, HttpSession session) {
		buyOfferAuxs(model, id, principal, session);	
		return "redirect:/offer/listothers";
	}
	
	@RequestMapping(value = "/home/offer/{id}/buy", method = RequestMethod.GET)
	public String buyOfferHome(Model model, Principal principal, @PathVariable Long id, HttpSession session) {
		buyOfferAuxs(model, id, principal, session);
		return "redirect:/home";
	}
	
	private void buyOfferAuxs(Model model,Long id, Principal principal, HttpSession session) {
		String email = principal.getName();
		User buyer = usersService.getUserByEmail(email);
		Offer offer = offersService.findById(id);
		if (buyer.getSaldo() >= offer.getCost()) {
			log.info("{} buys offer {}.", email, id);
			offersService.buyOffer(buyer, offer);
			session.setAttribute("saldo", buyer.getSaldo());
		} else {
			log.info("{} tries to buy offer {}, but hasn't enough money.", email, id);
		}
	}

	@RequestMapping("/offer/listpurchases")
	public String getListPurchases(Model model, Pageable pageable, Principal principal) {
		String email = principal.getName();
		User buyer = usersService.getUserByEmail(email);
		Page<Offer> purchases = offersService.getOffersBought(pageable, buyer);
		model.addAttribute("purchasesList", purchases.getContent());
		model.addAttribute("page", purchases);
		log.info("{} listing his purchases.", email);
		return "offer/listpurchases";
	}

	@RequestMapping("/offer/listpurchases/update")
	public String updateListPurchases(Model model, Pageable pageable, Principal principal) {
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		Page<Offer> offers = offersService.getOffersForUser(pageable, user);
		model.addAttribute("offerList", offers.getContent());
		log.info("Updating {} purchase list.", email);
		return "offer/purchaseslist :: tablePurchases";
	}
	
	@RequestMapping(value = "/offer/{id}/highlight", method = RequestMethod.GET)
	public String setHighlight(HttpSession session,Model model, @PathVariable Long id, Principal principal) {
		
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		if(user.getSaldo() >= 20) {
			log.info("{} highlights offer {}", email, id);
			user.setSaldo(user.getSaldo() - 20);
			session.setAttribute("saldo", user.getSaldo());
			usersService.updateUser(user);
			offersService.setOfferHighlight(true, id);
		}
		else {
			log.info("{} tries to highlight offer {}, but hasn't enough money.", email, id);
		}
		return "redirect:/offer/listown";
	}

	@RequestMapping(value = "/offer/{id}/nohighlight", method = RequestMethod.GET)
	public String setNoHighlight(Model model, @PathVariable Long id, Principal principal) {
		String email = principal.getName();
		log.info("{} removes highlight offer {}", email, id);
		offersService.setOfferHighlight(false, id);
		return "redirect:/offer/listown";
	}
	
	@RequestMapping("/offer/listown/update")
	public String updateListOwn(Model model, Pageable pageable, Principal principal) {
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		Page<Offer> offers = offersService.getOffersForUser(pageable, user);
		model.addAttribute("offerList", offers.getContent());
		log.info("Updating own offers table for {}", email);
		return "offer/listown :: tableOffers";
	}
	
	@RequestMapping("/offer/reload/saldo")
	public String updateSaldo(Model model, Pageable pageable, Principal principal) {
		log.info("Updating saldo for {}", principal.getName());
		return "offer/listown :: nav";
	}
	
	

}
