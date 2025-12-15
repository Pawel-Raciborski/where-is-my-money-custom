package org.whereismymoney.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record UserExpense(
        @NotNull UUID userId,
        @Positive @NotNull BigDecimal price
) {
}
