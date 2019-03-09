package com.uniovi.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Offer {

	@Id
	@GeneratedValue
	private Long id;
	private String title;
	private String description;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE) 
	private Date date;
	private Double cost;

	@ManyToOne
	private User seller;
	
	@ManyToOne
	private User buyer;

	public Offer(Long id, String title, String description, Date fecha, Double cost) {
		this.title = title;
		this.description = description;
		if(fecha!=null)
			this.date = fecha;
		else
			this.date = new Date(System.currentTimeMillis());
		this.cost = cost;
		this.id = id;
		this.buyer = null;
	}

	public Offer(String title, String description, Date fecha, Double cost, User seller) {
		super();
		this.title = title;
		this.description = description;
		if(fecha!=null)
			this.date = fecha;
		else
			this.date = new Date(System.currentTimeMillis());
		this.cost = cost;
		this.seller = seller;
	}

	public Offer() {
		super();
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date fecha) {
		this.date = fecha;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public Long getId() {
		return id;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

}
