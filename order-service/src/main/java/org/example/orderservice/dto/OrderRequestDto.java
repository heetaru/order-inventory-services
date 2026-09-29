package org.example.orderservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record OrderRequestDto(
        @NotNull
        Long productId,
        @NotNull
        @Positive
        Integer quantity
) {
}
