package com.uniovi.services;

import java.util.HashSet;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Chat;
import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.repositories.ChatsRepository;

@Service
public class ChatsService {
	
	@Autowired
	private ChatsRepository chatsRepo;
	
	@Autowired
	private MessagesService messagesService;
	
	public Page<Chat> getChatsForUser(Pageable pageable, User user) {
		Page<Chat> chats = chatsRepo.findByUser(pageable, user);
		return chats;
	}
	
	public void addChat(Chat chat) {
		chatsRepo.save(chat);
	}

	public Chat getChat(Long id) {
		Chat chat = chatsRepo.findById(id).get();
		chat.setMessages(messagesService.getMessagesForChat(chat));
		return chat;
	}
	
	public void deleteChat(Chat chat) {
		chatsRepo.deleteById(chat.getId());
	}
}
