package com.uniovi.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Chat {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Offer offer;

	@ManyToOne
	private User creator;

	@JsonIgnore
	@OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
	private List<Message> messages = new ArrayList<>();

	public Chat() {
	}

	public Chat(Offer offer, User creator) {
		this.offer = offer;
		this.creator = creator;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

}
