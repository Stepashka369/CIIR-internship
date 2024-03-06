package com.stepashka.crud.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Delivery {
	private Integer id;
	private LocalDate deliveryDate;
	private String curierFirstName;
	private String curierLastName;
	//OneToMany
	private Set<OrderDetail> orders = new HashSet<>();
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}
	
	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	public String getCurierFirstName() {
		return curierFirstName;
	}
	
	public void setCurierFirstName(String curierFirstName) {
		this.curierFirstName = curierFirstName;
	}
	
	public String getCurierLastName() {
		return curierLastName;
	}
	
	public void setCurierLastName(String curierLastName) {
		this.curierLastName = curierLastName;
	}

	public Set<OrderDetail> getOrders() {
		return orders;
	}

	public void setOrders(Set<OrderDetail> orders) {
		this.orders = orders;
	}	
}
