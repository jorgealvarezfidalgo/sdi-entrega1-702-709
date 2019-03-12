package com.uniovi.controllers;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import javax.servlet.http.HttpSession;

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
	public String setOffer(@Validated @ModelAttribute Offer offer, HttpSession session, BindingResult result, Model model,
			Principal principal) {
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		offer.setSeller(user);
		offerFormValidator.validate(offer, result);
		if (result.hasErrors()) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date today = new Date(System.currentTimeMillis());
			String todayAsString = df.format(today);
			model.addAttribute("currentDate", todayAsString);
			model.addAttribute("usersList", usersService.getUsers());
			return "offer/add";
		}	
		if(offer.isDestacada()) {
			user.setSaldo(user.getSaldo() - 20);
			usersService.updateUser(user);
			session.setAttribute("saldo", user.getSaldo());
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
		System.out.println(todayAsString);
		model.addAttribute("currentDate", todayAsString);
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

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
	}

	@RequestMapping("/offer/listothers")
	public String getListOthers(Model model, Pageable pageable, Principal principal,
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
		model.addAttribute("offerList", offers.getContent());
		model.addAttribute("page", offers);
		return "offer/listothers";
	}

	@RequestMapping("/offer/listothers/update")
	public String updateListOthers(Model model, Pageable pageable, Principal principal) {
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		Page<Offer> offers = offersService.getOtherUsersOffers(pageable, user);
		model.addAttribute("offerList", offers.getContent());
		return "offer/listothers :: tableOffers";
	}

	@RequestMapping(value = "/offer/{id}/buy", method = RequestMethod.GET)
	public String buyOffer(Model model, Principal principal, @PathVariable Long id, HttpSession session) {
		String email = principal.getName();
		User buyer = usersService.getUserByEmail(email);
		Offer offer = offersService.findById(id);
		if (buyer.getSaldo() >= offer.getCost()) {
			buyer.setSaldo(buyer.getSaldo() - offer.getCost());
			offer.setBuyer(buyer);
			offersService.addOffer(offer);
			usersService.updateUser(buyer);
			session.setAttribute("saldo", buyer.getSaldo());
		} else
			model.addAttribute("errorsaldo", "saldo insuficiente");
		
		System.out.println(offer.getBuyer().getEmail());
		return "redirect:/offer/listothers";
	}

	@RequestMapping("/offer/listpurchases")
	public String getListPurchases(Model model, Pageable pageable, Principal principal) {
		String email = principal.getName();
		User buyer = usersService.getUserByEmail(email);
		Page<Offer> purchases = offersService.getOffersBought(pageable, buyer);
		model.addAttribute("purchasesList", purchases.getContent());
		model.addAttribute("page", purchases);
		return "offer/listpurchases";
	}

	@RequestMapping("/offer/listpurchases/update")
	public String updateListPurchases(Model model, Pageable pageable, Principal principal) {
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		Page<Offer> offers = offersService.getOffersForUser(pageable, user);
		model.addAttribute("offerList", offers.getContent());
		return "offer/purchaseslist :: tablePurchases";
	}
	
	@RequestMapping(value = "/offer/{id}/highlight", method = RequestMethod.GET)
	public String setHighlight(HttpSession session,Model model, @PathVariable Long id, Principal principal) {
		offersService.setOfferHighlight(true, id);
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		user.setSaldo(user.getSaldo() - 20);
		session.setAttribute("saldo", user.getSaldo());
		usersService.updateUser(user);
		return "redirect:/offer/listown";
	}

	@RequestMapping(value = "/offer/{id}/nohighlight", method = RequestMethod.GET)
	public String setNoHighlight(Model model, @PathVariable Long id) {
		offersService.setOfferHighlight(false, id);
		return "redirect:/offer/listown";
	}
	
	@RequestMapping("/offer/listown/update")
	public String updateListOwn(Model model, Pageable pageable, Principal principal) {
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		Page<Offer> offers = offersService.getOffersForUser(pageable, user);
		model.addAttribute("offerList", offers.getContent());
		return "offer/listown :: tableOffers";
	}
	
	@RequestMapping("/offer/reload/saldo")
	public String updateSaldo(Model model, Pageable pageable, Principal principal) {
		return "offer/listown :: nav";
	}

}
