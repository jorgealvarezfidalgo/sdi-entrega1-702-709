package com.uniovi.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Chat {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private Offer offer;
	
	@ManyToOne
	private User creator;
	
	@JsonIgnoreProperties("chat")
	@OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
	private Set<Message> messages = new HashSet<>();

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

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

}
