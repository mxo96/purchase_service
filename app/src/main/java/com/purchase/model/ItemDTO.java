package com.purchase.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ItemDTO(
        @JsonProperty("product_id")
        Long productId,
        Integer quantity
) {
}
