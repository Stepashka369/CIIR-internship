package com.stepashka.crud.entity;

import java.util.HashSet;
import java.util.Set;

public class Client {
	private Integer id;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String address;
	//OneToMany
	private Set<OrderDetail> orders = new HashSet<>();
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}

	public Set<OrderDetail> getOrders() {
		return orders;
	}

	public void setOrders(Set<OrderDetail> orders) {
		this.orders = orders;
	}
}

