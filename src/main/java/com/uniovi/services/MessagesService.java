package com.uniovi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Chat;
import com.uniovi.entities.Message;
import com.uniovi.repositories.MessagesRepository;

@Service
public class MessagesService {

	@Autowired
	private MessagesRepository messagesRepo;

	public List<Message> getMessagesForChat(Chat chat) {
		return messagesRepo.findByChat(chat);
	}
	
	public void addMessage(Message message) {
		messagesRepo.save(message);
	}
}
