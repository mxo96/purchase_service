package com.purchase.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("order_id")
    Long orderId;

    @JsonProperty("order_price")
    BigDecimal orderPrice;
    @JsonProperty("order_vat")
    BigDecimal orderVat;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    List<OrderProductEntity> products;

    public OrderEntity() {
    }

    public OrderEntity(BigDecimal orderPrice, BigDecimal orderVat, List<OrderProductEntity> products) {
        this.orderPrice = orderPrice;
        this.orderVat = orderVat;
        this.products = products;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(final Long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(final BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public BigDecimal getOrderVat() {
        return orderVat;
    }

    public void setOrderVat(final BigDecimal orderVat) {
        this.orderVat = orderVat;
    }

    public List<OrderProductEntity> getProducts() {
        return products;
    }

    public void setProducts(final List<OrderProductEntity> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return Objects.equal(orderId, that.orderId) && Objects.equal(orderPrice, that.orderPrice) && Objects.equal(orderVat, that.orderVat) && Objects.equal(products, that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(orderId, orderPrice, orderVat, products);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("orderId", orderId)
                .add("orderPrice", orderPrice)
                .add("orderVat", orderVat)
                .add("products", products)
                .toString();
    }
}
