package com.purchase.validator;

import com.purchase.model.ItemDTO;
import com.purchase.model.OrderDTO;

import java.util.List;

public class RequestValidator {

    public static boolean isRequestValid(final OrderDTO orderDTO) {
        if (orderDTO.order() == null) {
            return false;
        }
        List<ItemDTO> items = orderDTO.order().items();
        if (items == null) {
            return false;
        }
        if (items.isEmpty()) {
            return false;
        }

        for (ItemDTO item : items) {
            if (item.productId() == null || item.quantity() == null || item.quantity() < 1) {
                return false;
            }
        }
        return true;
    }
}
