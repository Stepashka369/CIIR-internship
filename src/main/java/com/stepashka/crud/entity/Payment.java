package com.stepashka.crud.entity;

import java.time.LocalDate;

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
}
