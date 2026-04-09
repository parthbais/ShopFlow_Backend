package com.shopflow.service;

import com.shopflow.dto.request.OrderRequest;
import com.shopflow.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(OrderRequest request);
    OrderResponse getOrderById(Long id);
    List<OrderResponse> getOrdersByUser(Long userId);
    OrderResponse updateOrderStatus(Long id, String status);
}
