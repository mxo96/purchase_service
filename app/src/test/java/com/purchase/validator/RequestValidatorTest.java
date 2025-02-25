package com.purchase.validator;

import com.purchase.model.ItemDTO;
import com.purchase.model.ItemsDTO;
import com.purchase.model.OrderDTO;
import org.junit.Test;

import static com.purchase.validator.RequestValidator.isRequestValid;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RequestValidatorTest {
    @Test
    public void givenOrderDTOWithOrderNullWhenIsRequestValidThenShouldReturnFalse() {
        assertFalse(isRequestValid(new OrderDTO(null)));
    }

    @Test
    public void givenOrderDTOWithItemsNullWhenIsRequestValidThenShouldReturnFalse() {
        final ItemsDTO itemsDTO = new ItemsDTO(null);
        final OrderDTO orderDTO = new OrderDTO(itemsDTO);
        assertFalse(isRequestValid(orderDTO));
    }

    @Test
    public void givenOrderDTOWithItemsEmptyWhenIsRequestValidThenShouldReturnFalse() {
        final ItemsDTO itemsDTO = new ItemsDTO(emptyList());
        final OrderDTO orderDTO = new OrderDTO(itemsDTO);
        assertFalse(isRequestValid(orderDTO));
    }

    @Test
    public void givenOrderDTOWithItemProductIdNullWhenIsRequestValidThenShouldReturnFalse() {
        final ItemDTO item = new ItemDTO(null, 1);
        final ItemsDTO itemsDTO = new ItemsDTO(singletonList(item));
        final OrderDTO orderDTO = new OrderDTO(itemsDTO);
        assertFalse(isRequestValid(orderDTO));
    }

    @Test
    public void givenOrderDTOWithItemQuantityNullWhenIsRequestValidThenShouldReturnFalse() {
        final ItemDTO item = new ItemDTO(1L, null);
        final ItemsDTO itemsDTO = new ItemsDTO(singletonList(item));
        final OrderDTO orderDTO = new OrderDTO(itemsDTO);
        assertFalse(isRequestValid(orderDTO));
    }

    @Test
    public void givenOrderDTOWithItemQuantityLowerThenOneWhenIsRequestValidThenShouldReturnFalse() {
        final ItemDTO item = new ItemDTO(1L, 0);
        final ItemsDTO itemsDTO = new ItemsDTO(singletonList(item));
        final OrderDTO orderDTO = new OrderDTO(itemsDTO);
        assertFalse(isRequestValid(orderDTO));
    }

    @Test
    public void givenOrderDTOWithItemsWhenIsRequestValidThenShouldReturnTrue() {
        final ItemDTO item = new ItemDTO(1L, 1);
        final ItemsDTO itemsDTO = new ItemsDTO(singletonList(item));
        final OrderDTO orderDTO = new OrderDTO(itemsDTO);
        assertTrue(isRequestValid(orderDTO));
    }
}