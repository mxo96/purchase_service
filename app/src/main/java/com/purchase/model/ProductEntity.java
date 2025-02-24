package com.purchase.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private BigDecimal price;
    private BigDecimal vat;

    public ProductEntity() {
    }

    public ProductEntity(final BigDecimal price, final BigDecimal vat) {
        this.price = price;
        this.vat = vat;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(final Long productId) {
        this.productId = productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getVat() {
        return vat;
    }

    public void setVat(final BigDecimal vat) {
        this.vat = vat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return Objects.equal(productId, that.productId) && Objects.equal(price, that.price) && Objects.equal(vat, that.vat);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productId, price, vat);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("productId", productId)
                .add("price", price)
                .add("vat", vat)
                .toString();
    }
}