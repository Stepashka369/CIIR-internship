package com.stepashka.hibernate.dto;

import java.time.LocalDate;

public class DeliveryDTO {
    private Integer id;
    private LocalDate deliveryDate;
    private String courierFistName;
    private String courierLastName;

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

    public String getCourierFistName() {
        return courierFistName;
    }

    public void setCourierFistName(String courierFistName) {
        this.courierFistName = courierFistName;
    }

    public String getCourierLastName() {
        return courierLastName;
    }

    public void setCourierLastName(String courierLastName) {
        this.courierLastName = courierLastName;
    }
}
