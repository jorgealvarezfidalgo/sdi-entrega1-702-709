package com.uniovi.controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Chat;
import com.uniovi.entities.Message;
import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.services.ChatsService;
import com.uniovi.services.MessagesService;
import com.uniovi.services.OffersService;
import com.uniovi.services.UsersService;

@Controller
public class ChatsController {

	@Autowired
	private UsersService usersService;

	@Autowired
	private OffersService offersService;

	@Autowired
	private ChatsService chatsService;
	
	@Autowired
	private MessagesService messagesService;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/chat/list")
	public String getList(HttpSession session, Model model, Pageable pageable, Principal principal) {
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		Page<Chat> chats = chatsService.getChatsForUser(pageable, user);

		log.info("{} listing his chats.", email);
		model.addAttribute("chatList", chats.getContent());
		model.addAttribute("page", chats);
		return "chat/list";
	}

	@RequestMapping("/chat/chat/{id}")
	public String getChat(Model model, @PathVariable Long id, Principal principal) {
		model.addAttribute("chat", chatsService.getChat(id));
		log.info("{} opens chat {}.", principal.getName(), id);
		return "chat/chat";
	}

	@RequestMapping(value = "/chat/add/{id}")
	public String addChat(HttpSession session, Model model, Principal principal, @PathVariable Long id) {
		String email = principal.getName();
		User creator = usersService.getUserByEmail(email);
		Offer offer = offersService.findById(id);
		Chat newChat = new Chat(offer, creator);
		chatsService.addChat(newChat);
		long chatId = newChat.getId();
		log.info("Creating chat {}", chatId);
		model.addAttribute("chat", newChat);
		return "redirect:/chat/chat/" + chatId;
	}

	@RequestMapping(value = "/chat/chat/{id}/addmessage")
	public String addMessage(HttpSession session, Model model, Principal principal, @PathVariable Long id,
			@RequestParam(value = "", required = true) String messageText) {
		String email = principal.getName();
		User sender = usersService.getUserByEmail(email);
		Chat chat = chatsService.getChat(id);
		Message newMessage = new Message(chat, sender, messageText);
		log.info("New message at chat {}", id);
		messagesService.addMessage(newMessage);
		return "redirect:/chat/chat/" + id; 
	}

}
