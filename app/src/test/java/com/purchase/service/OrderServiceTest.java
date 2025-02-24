package com.purchase.service;

import com.purchase.exception.ProductNotFoundException;
import com.purchase.model.ItemDTO;
import com.purchase.model.OrderEntity;
import com.purchase.model.OrderProductEntity;
import com.purchase.model.OrderRepository;
import com.purchase.model.ProductEntity;
import com.purchase.model.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private OrderService orderService;

    @Before
    public void setUp() {
    }

    @Test
    public void givenOneOrderDTOWhenCreateOrderShouldReturnOrderEntity() {
        final ItemDTO orderDTO = new ItemDTO(1L, 3);
        final ProductEntity product = new ProductEntity(
                BigDecimal.ONE,
                BigDecimal.valueOf(0.1));
        final OrderEntity order = new OrderEntity(
                BigDecimal.valueOf(3L),
                BigDecimal.valueOf(0.3),
                new ArrayList<>());

        final OrderProductEntity orderProductEntity = new OrderProductEntity(
                1L,
                3,
                BigDecimal.ONE,
                BigDecimal.valueOf(0.1),
                order);
        order.setProducts(singletonList(orderProductEntity));

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(order);

        final OrderEntity createdOrder = orderService.createOrder(List.of(orderDTO));

        verify(orderRepository, times(2)).save(any(OrderEntity.class));
        verify(productRepository, times(1)).findById(orderDTO.productId());

        assertEquals(BigDecimal.valueOf(3), createdOrder.getOrderPrice());
        assertEquals(BigDecimal.valueOf(0.3), createdOrder.getOrderVat());
    }

    @Test
    public void givenMoreOrderDTOWhenCreateOrderShouldReturnOrderEntity() {
        final ItemDTO orderDTO = new ItemDTO(1L, 3);
        final ItemDTO orderDTO2 = new ItemDTO(2L, 5);

        final ProductEntity product = new ProductEntity(
                BigDecimal.ONE,
                BigDecimal.valueOf(0.1));
        final ProductEntity product2 = new ProductEntity(
                BigDecimal.TEN,
                BigDecimal.ONE);
        final OrderEntity order = new OrderEntity(
                BigDecimal.valueOf(53L),
                BigDecimal.valueOf(5.3),
                new ArrayList<>());

        final OrderProductEntity orderProductEntity = new OrderProductEntity(
                1L,
                3,
                BigDecimal.ONE,
                BigDecimal.valueOf(0.1),
                order);
        final OrderProductEntity orderProductEntity2 = new OrderProductEntity(
                2L,
                5,
                BigDecimal.TEN,
                BigDecimal.ONE, order);
        order.setProducts(List.of(orderProductEntity, orderProductEntity2));

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.findById(2L)).thenReturn(Optional.of(product2));
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(order);

        final OrderEntity createdOrder = orderService.createOrder(List.of(orderDTO, orderDTO2));

        verify(orderRepository, times(2)).save(any(OrderEntity.class));
        verify(productRepository, times(1)).findById(orderDTO.productId());
        verify(productRepository, times(1)).findById(orderDTO2.productId());

        assertEquals(BigDecimal.valueOf(53), createdOrder.getOrderPrice());
        assertEquals(BigDecimal.valueOf(5.3), createdOrder.getOrderVat());
    }

    @Test(expected = ProductNotFoundException.class)
    public void givenOrderDTOWhenProductIsNotFoundThenShouldThrowProductNotFoundException() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());
        orderService.createOrder(singletonList(new ItemDTO(1L, 1)));

        verify(orderRepository, times(1)).save(any(OrderEntity.class));
        verify(productRepository, times(1)).findById(1L);
    }
}