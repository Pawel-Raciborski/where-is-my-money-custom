package org.whereismymoney.controllers.v2.dto;

import jakarta.persistence.OneToOne;
import org.whereismymoney.model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ExpenseDto(
        Long id,
        String name,
        UUID owner,
        BigDecimal totalAmount,
        boolean isCustom,
        LocalDateTime time,
        UUID groupId
) {
}
