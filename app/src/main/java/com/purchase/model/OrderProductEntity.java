package com.purchase.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "order_products")
public class OrderProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    Long id;

    @JsonProperty("product_id")
    Long productId;
    Integer quantity;
    BigDecimal price;
    BigDecimal vat;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @JsonIgnore
    OrderEntity order;

    public OrderProductEntity() {
    }

    public OrderProductEntity(Long productId, Integer quantity, BigDecimal price, BigDecimal vat, OrderEntity order) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.vat = vat;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getVat() {
        return vat;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProductEntity that = (OrderProductEntity) o;
        return Objects.equal(id, that.id) && Objects.equal(productId, that.productId) && Objects.equal(quantity, that.quantity) && Objects.equal(price, that.price) && Objects.equal(vat, that.vat) && Objects.equal(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, productId, quantity, price, vat, order);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("productId", productId)
                .add("quantity", quantity)
                .add("price", price)
                .add("vat", vat)
                .add("order", order)
                .toString();
    }
}
