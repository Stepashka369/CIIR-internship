package com.stepashka.jsptask.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Payment {
	private Integer id;
	private LocalDate date;
	private Float sum;

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
		Payment other = (Payment) obj;
		return Objects.equals(date, other.date) && Objects.equals(id, other.id) && Objects.equals(sum, other.sum);
	}

}
