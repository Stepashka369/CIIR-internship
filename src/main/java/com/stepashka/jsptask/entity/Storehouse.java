package com.stepashka.jsptask.entity;

import java.util.Map;
import java.util.Objects;

public class Storehouse {
	private Integer id;
	private String address;
	private Float square;
	// ManyToMany
	private Map<Good, Integer> goods;

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

	public Map<Good, Integer> getGoods() {
		return goods;
	}

	public void setGoods(Map<Good, Integer> goods) {
		this.goods = goods;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, goods, id, square);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Storehouse other = (Storehouse) obj;
		return Objects.equals(address, other.address) && Objects.equals(goods, other.goods)
				&& Objects.equals(id, other.id) && Objects.equals(square, other.square);
	}
}
