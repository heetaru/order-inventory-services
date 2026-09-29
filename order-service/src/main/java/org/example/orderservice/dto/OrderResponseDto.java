package org.example.orderservice.dto;

import java.math.BigDecimal;

public record OrderResponseDto(
        Long id,
        Long productId,
        Integer quantity,
        String status
) {
}
