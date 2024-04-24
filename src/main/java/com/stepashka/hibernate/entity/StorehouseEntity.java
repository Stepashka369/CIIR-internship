package com.stepashka.hibernate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "storehouse")
public class StorehouseEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "address")
	private String address;

	@Column(name = "square")
	private Float square;

	@ManyToMany
	@JoinTable(name = "good_storehouse",
			joinColumns = @JoinColumn(name = "id_stock"),
			inverseJoinColumns = @JoinColumn(name = "id_good"))
	private Set<GoodEntity> goods;

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

	public Set<GoodEntity> getGoods() {
		return goods;
	}

	public void setGoods(Set<GoodEntity> goods) {
		this.goods = goods;
	}

	public Boolean addGood(GoodEntity good){
		return this.goods.add(good);
	}

	public Boolean deleteGood(GoodEntity good) {
		return this.goods.remove(good);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		StorehouseEntity that = (StorehouseEntity) o;
		return Objects.equals(id, that.id) && Objects.equals(address, that.address) && Objects.equals(square, that.square) && Objects.equals(goods, that.goods);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, address, square, goods);
	}
}
