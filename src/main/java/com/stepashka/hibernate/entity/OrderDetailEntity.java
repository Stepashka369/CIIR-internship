package com.stepashka.hibernate.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "order_detail")
public class OrderDetailEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "code")
    private String code;

    @Column(name = "status")
    private String status;

    @OneToOne(mappedBy = "order")
    private PaymentEntity payment;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private ClientEntity client;

    @OneToOne
    @JoinColumn(name = "id_delivery")
    private DeliveryEntity delivery;

    @ManyToMany
    @JoinTable(name = "good_order",
            joinColumns = @JoinColumn(name = "id_order"),
            inverseJoinColumns = @JoinColumn(name = "id_good"))
    private Set<GoodEntity> goods;

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

    public DeliveryEntity getDelivery() {
        return delivery;
    }

    public void setDelivery(DeliveryEntity delivery) {
        this.delivery = delivery;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public Set<GoodEntity> getGoods() {
        return goods;
    }

    public void setGoods(Set<GoodEntity> goods) {
        this.goods = goods;
    }

    public PaymentEntity getPayment() {
        return payment;
    }

    public void setPayment(PaymentEntity payment) {
        this.payment = payment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetailEntity that = (OrderDetailEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(orderDate, that.orderDate) && Objects.equals(code, that.code) && Objects.equals(status, that.status) && Objects.equals(payment, that.payment) && Objects.equals(client, that.client) && Objects.equals(delivery, that.delivery) && Objects.equals(goods, that.goods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderDate, code, status, payment, client, delivery, goods);
    }
}
