package com.stepashka.hibernate.dto;

import java.time.LocalDate;
import java.util.Set;

public class OrderDetailDTO {
    private Integer id;
    private LocalDate orderDate;
    private String code;
    private String status;
    private PaymentDTO payment;
    private DeliveryDTO delivery;
    private Set<GoodDTO> goods;

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

    public PaymentDTO getPayment() {
        return payment;
    }

    public void setPayment(PaymentDTO payment) {
        this.payment = payment;
    }

    public DeliveryDTO getDelivery() {
        return delivery;
    }

    public void setDelivery(DeliveryDTO delivery) {
        this.delivery = delivery;
    }

    public Set<GoodDTO> getGoods() {
        return goods;
    }

    public void setGoods(Set<GoodDTO> goods) {
        this.goods = goods;
    }
}
