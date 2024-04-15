package com.stepashka.hibernate.entity;

import jakarta.persistence.*;

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
	public int hashCode() {
		return Objects.hash(courierFirstName, courierLastName, deliveryDate, id, order);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeliveryEntity other = (DeliveryEntity) obj;
		return Objects.equals(courierFirstName, other.courierFirstName)
				&& Objects.equals(courierLastName, other.courierLastName)
				&& Objects.equals(deliveryDate, other.deliveryDate) && Objects.equals(id, other.id)
				&& Objects.equals(order, other.order);
	}
}
