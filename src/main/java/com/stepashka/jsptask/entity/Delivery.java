package com.stepashka.jsptask.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Delivery {
	private Integer id;
	private LocalDate deliveryDate;
	private String courierFirstName;
	private String courierLastName;
	private OrderDetail order;

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

	public OrderDetail getOrder() {
		return order;
	}

	public void setOrder(OrderDetail order) {
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
		Delivery other = (Delivery) obj;
		return Objects.equals(courierFirstName, other.courierFirstName)
				&& Objects.equals(courierLastName, other.courierLastName)
				&& Objects.equals(deliveryDate, other.deliveryDate) && Objects.equals(id, other.id)
				&& Objects.equals(order, other.order);
	}
}
