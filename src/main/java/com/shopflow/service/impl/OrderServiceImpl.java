package com.shopflow.service.impl;

import com.shopflow.config.AppConstants;
import com.shopflow.dto.request.OrderRequest;
import com.shopflow.dto.response.OrderResponse;
import com.shopflow.entity.Order;
import com.shopflow.entity.OrderStatus;
import com.shopflow.entity.Product;
import com.shopflow.entity.User;
import com.shopflow.exception.ResourceNotFoundException;
import com.shopflow.mapper.OrderMapper;
import com.shopflow.repository.OrderRepository;
import com.shopflow.repository.ProductRepository;
import com.shopflow.repository.UserRepository;
import com.shopflow.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository   orderRepository;
    private final UserRepository    userRepository;
    private final ProductRepository productRepository;
    private final OrderMapper       orderMapper;

    @Override
    public OrderResponse createOrder(OrderRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + request.getUserId()));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + request.getProductId()));

        BigDecimal totalPrice = product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity()));

        Order saved = orderRepository.save(Order.builder()
                .user(user)
                .product(product)
                .quantity(request.getQuantity())
                .totalPrice(totalPrice)
                .status(OrderStatus.valueOf(AppConstants.INITIAL_ORDER_STATUS))
                .build());

        log.info("Order created: id={}", saved.getId());
        return orderMapper.toResponse(saved);
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        return orderMapper.toResponse(findOrThrow(id));
    }

    @Override
    public List<OrderResponse> getOrdersByUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found: " + userId);
        }
        return orderRepository.findByUserId(userId).stream()
                .map(orderMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponse updateOrderStatus(Long id, String status) {
        Order order = findOrThrow(id);
        try {
            order.setStatus(OrderStatus.valueOf(status.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                "Invalid status: " + status + ". Valid: PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED");
        }
        log.info("Order {} status -> {}", id, status.toUpperCase());
        return orderMapper.toResponse(orderRepository.save(order));
    }

    private Order findOrThrow(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + id));
    }
}
