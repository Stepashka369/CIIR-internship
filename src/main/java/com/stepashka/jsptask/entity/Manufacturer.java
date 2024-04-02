package com.stepashka.jsptask.entity;

import java.util.Objects;
import java.util.Set;

public class Manufacturer {
	private Integer id;
	private String name;
	private String country;
	// OneToMany
	private Set<Good> goods;

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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Set<Good> getGoods() {
		return goods;
	}

	public void setGoods(Set<Good> goods) {
		this.goods = goods;
	}

	@Override
	public int hashCode() {
		return Objects.hash(country, goods, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Manufacturer other = (Manufacturer) obj;
		return Objects.equals(country, other.country) && Objects.equals(goods, other.goods)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
}
