package com.purchase.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.purchase.model.ItemDTO;
import com.purchase.model.ItemsDTO;
import com.purchase.model.OrderDTO;
import com.purchase.model.OrderEntity;
import com.purchase.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;

import static java.util.Collections.singletonList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OrderService orderService;

    @Test
    public void givenOrderDTOWhenCreateOrderThenReturn200() throws Exception {
        final ItemDTO item = new ItemDTO(1L, 1);
        final ItemsDTO itemsDTO = new ItemsDTO(singletonList(item));
        final OrderDTO orderDTO = new OrderDTO(itemsDTO);
        final OrderEntity orderEntity = new OrderEntity(BigDecimal.valueOf(7), BigDecimal.valueOf(0.7), new ArrayList<>());

        when(orderService.createOrder(any())).thenReturn(orderEntity);

        mockMvc.perform(post("/api/v1/createOrder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.order_price").value(7))
                .andExpect(jsonPath("$.order_vat").value(0.7));
    }

    @Test
    public void givenInvalidOrderDTOWhenCreateOrderThenReturn400() throws Exception {
        OrderDTO invalidOrderDTO = new OrderDTO(null);

        mockMvc.perform(post("/api/v1/createOrder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidOrderDTO)))
                .andExpect(status().isBadRequest());
    }
}
