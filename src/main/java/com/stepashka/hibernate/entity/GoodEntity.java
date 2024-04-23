package com.stepashka.hibernate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "good")
public class GoodEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "product_name")
	private String name;

	@Column(name = "model")
	private String model;

	@Column(name = "category")
	private String category;

	@Column(name = "guarantee")
	private Integer guarantee;

	@Column(name = "price")
	private Float price;

	@Column(name = "description")
	private String description;

	@ManyToOne
	@JoinColumn(name = "id_manufacturer")
	private ManufacturerEntity manufacturer;

	@ManyToMany
	@JoinTable(name = "good_order",
			joinColumns = @JoinColumn(name = "id_good"),
			inverseJoinColumns = @JoinColumn(name = "id_order"))
	private Set<OrderDetailEntity> orders;

	@ManyToMany
	@JoinTable(name = "good_storehouse",
			joinColumns = @JoinColumn(name = "id_good"),
			inverseJoinColumns = @JoinColumn(name = "id_storehouse"))
	private Set<StorehouseEntity> storehouses;

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ManufacturerEntity getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(ManufacturerEntity manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Set<OrderDetailEntity> getOrders() {
		return orders;
	}

	public void setOrders(Set<OrderDetailEntity> orders) {
		this.orders = orders;
	}

	public Set<StorehouseEntity> getStorehouses() {
		return storehouses;
	}

	public void setStorehouses(Set<StorehouseEntity> storehouses) {
		this.storehouses = storehouses;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		GoodEntity that = (GoodEntity) o;
		return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(model, that.model) && Objects.equals(guarantee, that.guarantee) && Objects.equals(price, that.price) && Objects.equals(description, that.description) && Objects.equals(manufacturer, that.manufacturer) && Objects.equals(orders, that.orders) && Objects.equals(storehouses, that.storehouses);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, model, guarantee, price, description, manufacturer, orders, storehouses);
	}
}
