package com.uniovi.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {

	@Id
	@GeneratedValue
	private long id;
	@Column(unique = true)
	private String email;
	private String name;
	private String lastName;
	private String role;
	private double saldo;

	private String password;
	@Transient // propiedad que no se almacena e la tabla.
	private String passwordConfirm;

	@JsonIgnore
	@OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
	private Set<Offer> offers = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
	private Set<Offer> purchases = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
	private Set<Chat> createdChats  = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
	private Set<Message> sendedmessages = new HashSet<>();

	public User(String email, String name, String lastName) {
		super();
		this.email = email;
		this.name = name;
		this.lastName = lastName;
		saldo = 100;
	}

	public User() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return this.name + " " + this.lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Set<Offer> getOffers() {
		return offers;
	}

	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}

	public Set<Offer> getPurchases() {
		return purchases;
	}

	public void setPurchases(Set<Offer> purchases) {
		this.purchases = purchases;
	}

}