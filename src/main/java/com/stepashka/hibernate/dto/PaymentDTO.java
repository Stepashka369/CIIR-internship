package com.stepashka.hibernate.dto;

import jakarta.persistence.criteria.CriteriaBuilder;

public class PaymentDTO {
    private Integer id;
    private Float sum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getSum() {
        return sum;
    }

    public void setSum(Float sum) {
        this.sum = sum;
    }
}
