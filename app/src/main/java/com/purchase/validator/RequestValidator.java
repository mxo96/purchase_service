package com.purchase.validator;

import com.purchase.model.OrderDTO;

public class RequestValidator {

    public static boolean isRequestValid(final OrderDTO orderDTO) {
        if (orderDTO.order() == null) {
            return false;
        }
        if (orderDTO.order().items() == null) {
            return false;
        }
        return !orderDTO.order().items().isEmpty();
    }
}
