package com.shopflow.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class OrderResponse {
    private Long          id;
    private String        userName;
    private String        productName;
    private Integer       quantity;
    private BigDecimal    totalPrice;
    private String        status;
    private LocalDateTime createdAt;
}
