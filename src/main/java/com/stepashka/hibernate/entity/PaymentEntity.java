package com.stepashka.hibernate.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "payment")
public class PaymentEntity {

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "payment_date")
	private LocalDate date;

	@Column(name = "payment_sum")
	private Float sum;

	@OneToOne
	@JoinColumn(name = "id_order")
	private OrderDetailEntity order;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Float getSum() {
		return sum;
	}

	public void setSum(Float sum) {
		this.sum = sum;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, id, sum);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaymentEntity other = (PaymentEntity) obj;
		return Objects.equals(date, other.date) && Objects.equals(id, other.id) && Objects.equals(sum, other.sum);
	}

}
