package com.stepashka.crud.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Delivery {
	private Integer id;
	private LocalDate deliveryDate;
	private String curierFirstName;
	private String curierLastName;
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

	public String getCurierFirstName() {
		return curierFirstName;
	}

	public void setCurierFirstName(String curierFirstName) {
		this.curierFirstName = curierFirstName;
	}

	public String getCurierLastName() {
		return curierLastName;
	}

	public void setCurierLastName(String curierLastName) {
		this.curierLastName = curierLastName;
	}

	public OrderDetail getOrder() {
		return order;
	}

	public void setOrder(OrderDetail order) {
		this.order = order;
	}

	@Override
	public int hashCode() {
		return Objects.hash(curierFirstName, curierLastName, deliveryDate, id, order);
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
		return Objects.equals(curierFirstName, other.curierFirstName)
				&& Objects.equals(curierLastName, other.curierLastName)
				&& Objects.equals(deliveryDate, other.deliveryDate) && Objects.equals(id, other.id)
				&& Objects.equals(order, other.order);
	}
}
