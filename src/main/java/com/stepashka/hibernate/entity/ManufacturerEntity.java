package com.stepashka.hibernate.entity;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "manufacturer")
public class ManufacturerEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "manufacturer_name")
	private String name;

	@Column(name = "country")
	private String country;

	@OneToMany(mappedBy = "manufacturer")
	private Set<GoodEntity> goods;

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

	public Set<GoodEntity> getGoods() {
		return goods;
	}

	public void setGoods(Set<GoodEntity> goods) {
		this.goods = goods;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ManufacturerEntity that = (ManufacturerEntity) o;
		return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(country, that.country) && Objects.equals(goods, that.goods);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, country, goods);
	}
}
