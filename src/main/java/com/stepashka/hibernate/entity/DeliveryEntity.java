package com.stepashka.hibernate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "delivery")
public class DeliveryEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "delivery_date")
	private LocalDate deliveryDate;

	@Column(name = "curier_first_name")
	private String courierFirstName;

	@Column(name = "curier_last_name")
	private String courierLastName;

	@OneToOne(mappedBy = "delivery")
	private OrderDetailEntity order;

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

	public String getCourierFirstName() {
		return courierFirstName;
	}

	public void setCourierFirstName(String courierFirstName) {
		this.courierFirstName = courierFirstName;
	}

	public String getCourierLastName() {
		return courierLastName;
	}

	public void setCourierLastName(String courierLastName) {
		this.courierLastName = courierLastName;
	}

	public OrderDetailEntity getOrder() {
		return order;
	}

	public void setOrder(OrderDetailEntity order) {
		this.order = order;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DeliveryEntity that = (DeliveryEntity) o;
		return Objects.equals(id, that.id) && Objects.equals(deliveryDate, that.deliveryDate) && Objects.equals(courierFirstName, that.courierFirstName) && Objects.equals(courierLastName, that.courierLastName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, deliveryDate, courierFirstName, courierLastName);
	}
}
