package com.uniovi.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
public class Message {

	@Id
	@GeneratedValue
	private Long id;
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	private String text;

	@ManyToOne
	private User sender;

	@ManyToOne
	private Chat chat;

	public Message() {
	}

	public Message(Chat chat, User sender, String text) {
		this.chat = chat;
		this.sender = sender;
		this.text = text;
		this.date = new Date();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

}
