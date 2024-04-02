package com.stepashka.jsptask.entity;

import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

public class OrderDetail {
	private Integer id;
	private LocalDate orderDate;
	private String code;
	private String status;
	private Payment payment;
	// ManyToMany
	private Map<Good, Integer> goods;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Map<Good, Integer> getGoods() {
		return goods;
	}

	public void setGoods(Map<Good, Integer> goods) {
		this.goods = goods;
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, goods, id, orderDate, payment, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetail other = (OrderDetail) obj;
		return Objects.equals(code, other.code) && Objects.equals(goods, other.goods) && Objects.equals(id, other.id)
				&& Objects.equals(orderDate, other.orderDate) && Objects.equals(payment, other.payment)
				&& Objects.equals(status, other.status);
	}

}
