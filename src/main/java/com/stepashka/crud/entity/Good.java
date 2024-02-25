package com.stepashka.crud.entity;

import java.util.HashSet;
import java.util.Set;

public class Good {
	private Integer id;
	private String name;
	private String model;
	private Integer guarantee;
	private Float price;
	private String description;
	private Integer numberOfUnits; //для стороны товара указывает общее количество товара на всех складах, для стороны склада количество товара на конкретном складе 
	//ManyToOne
	private Manufacturer manufacturer;
	//ManyToMany
	private Set<Storehouse> storehouses = new HashSet<>();
	
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

	public Integer getNumberOfUnits() {
		return numberOfUnits;
	}

	public void setNumberOfUnits(Integer numberOfUnits) {
		this.numberOfUnits = numberOfUnits;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Set<Storehouse> getStorehouses() {
		return storehouses;
	}

	public void setStorehouses(Set<Storehouse> storehouses) {
		this.storehouses = storehouses;
	}
	
}
