package com.purchase.controller;

import com.purchase.model.OrderDTO;
import com.purchase.model.OrderEntity;
import com.purchase.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.purchase.validator.RequestValidator.isRequestValid;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
    private final OrderService orderService;

    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/createOrder")
    public ResponseEntity<?> createOrder(@RequestBody OrderDTO orderDTO) {
        if (!isRequestValid(orderDTO)) {
            return ResponseEntity.badRequest().body("The request body is not valid, check the documentation");
        }

        final OrderEntity orderEntity = orderService.createOrder(orderDTO.order().items());
        return ResponseEntity.ok(orderEntity);
    }
}
