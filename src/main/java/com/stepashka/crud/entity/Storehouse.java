package com.stepashka.crud.entity;

import java.util.HashSet;
import java.util.Set;

public class Storehouse {
	private Integer id;
	private String address;
	private Float square;
	//ManyToMany
	private Set<Good> goods = new HashSet<>();
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Float getSquare() {
		return square;
	}
	
	public void setSquare(Float square) {
		this.square = square;
	}

	public Set<Good> getGoods() {
		return goods;
	}

	public void setGoods(Set<Good> goods) {
		this.goods = goods;
	}
}
