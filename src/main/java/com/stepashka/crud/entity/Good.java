package com.stepashka.crud.entity;

import java.util.Map;
import java.util.Set;

public class Good {
	private Integer id;
	private String name;
	private String model;
	private Integer guarantee;
	private Float price;
	private String description;
	private Manufacturer manufacturer;
	//ManyToMany
	private Map<Storehouse, Integer> storehouses;
	//ManyToMany
	private Set<OrderDetail> orders;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getGuarantee() {
		return guarantee;
	}

	public void setGuarantee(Integer guarantee) {
		this.guarantee = guarantee;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<Storehouse, Integer> getStorehouses() {
		return storehouses;
	}

	public void setStorehouses(Map<Storehouse, Integer> storehouses) {
		this.storehouses = storehouses;
	}

	public Set<OrderDetail> getOrders() {
		return orders;
	}

	public void setOrders(Set<OrderDetail> orders) {
		this.orders = orders;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}	
}
