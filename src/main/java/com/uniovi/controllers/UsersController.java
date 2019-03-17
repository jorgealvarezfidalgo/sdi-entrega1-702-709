package com.uniovi.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.services.OffersService;
import com.uniovi.services.RolesService;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.SignUpFormValidator;

@Controller
public class UsersController {

	@Autowired
	private RolesService rolesService;

	@Autowired
	private UsersService usersService;
	
	@Autowired
	private OffersService offersService;
	
	@Autowired
	private SignUpFormValidator signUpFormValidator;
	
	@Autowired
	private SecurityService securityService;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("/user/list")
	public String getListado(Model model, Pageable pageable) {
		Page<User> users = usersService.getUsers(pageable);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		log.info("Administrador {} lista usuarios.", email);
		model.addAttribute("email", email);
		model.addAttribute("usersList", users.getContent());
		model.addAttribute("page", users);
		return "user/list";
	}
	
	@RequestMapping("/user/delete/{id}")
	public String delete(@PathVariable Long id, Pageable pageable, Model model) {
		usersService.deleteUser(id);
		Page<User> users = usersService.getUsers(pageable);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		log.info("Administrador {} borra al usuario {}", email, id);
		model.addAttribute("email", email);
		model.addAttribute("usersList", users.getContent());
		model.addAttribute("page", users);
		return "user/list :: tableUsers";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@Validated User user, BindingResult result, Model model) {
		signUpFormValidator.validate(user, result);
		if (result.hasErrors()) {
			return "signup";
		}
		log.info("Usuario registrado con email {}, nombre {} y apellidos {}.", user.getEmail(), user.getName(), user.getLastName());

		user.setRole(rolesService.getRoles()[0]);
		user.setSaldo(100);
		usersService.addUser(user);
		securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
		
		return "redirect:home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(HttpSession session, Model model, Pageable pageable) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		log.info("{} accede a Home.", email);
		Page<Offer> offers = offersService.getHighlightedOffers(pageable, activeUser);
		model.addAttribute("offerList", offers.getContent());
		model.addAttribute("page", offers);
		session.setAttribute("saldo", activeUser.getSaldo());
		return "home";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	    	log.info("{} cierra sesión.", auth.getName());
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:login";
	}
	
	@RequestMapping(value="/403", method = RequestMethod.GET)
	public String denyAccess() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		log.info("{} intenta acceder a un recurso prohibido.", email);
		return "accessdenied";
	}
}
