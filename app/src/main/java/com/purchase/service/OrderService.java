package com.purchase.service;

import com.purchase.exception.ProductNotFoundException;
import com.purchase.model.ItemDTO;
import com.purchase.model.OrderEntity;
import com.purchase.model.OrderProductEntity;
import com.purchase.model.OrderRepository;
import com.purchase.model.ProductEntity;
import com.purchase.model.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderService(final OrderRepository orderRepository, final ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public OrderEntity createOrder(final List<ItemDTO> items) throws RuntimeException {
        BigDecimal totalOrderPrice = BigDecimal.ZERO;
        BigDecimal totalOrderVat = BigDecimal.ZERO;

        final List<OrderProductEntity> orderProducts = new ArrayList<>();
        final OrderEntity order = new OrderEntity(BigDecimal.ZERO, BigDecimal.ZERO, new ArrayList<>());

        final OrderEntity savedOrder = orderRepository.save(order);

        for (final ItemDTO item : items) {
            final ProductEntity product = productRepository.findById(item.productId())
                    .orElseThrow(() -> new ProductNotFoundException("Product not found: " + item.productId()));

            final BigDecimal itemTotalPrice = product.getPrice().multiply(BigDecimal.valueOf(item.quantity()));
            final BigDecimal itemTotalVat = product.getVat().multiply(BigDecimal.valueOf(item.quantity()));

            orderProducts.add(new OrderProductEntity(
                    item.productId(),
                    item.quantity(),
                    product.getPrice(),
                    product.getVat(),
                    savedOrder));

            totalOrderPrice = totalOrderPrice.add(itemTotalPrice);
            totalOrderVat = totalOrderVat.add(itemTotalVat);
        }

        savedOrder.setOrderPrice(totalOrderPrice);
        savedOrder.setOrderVat(totalOrderVat);
        savedOrder.setProducts(orderProducts);

        return orderRepository.save(savedOrder);
    }
}