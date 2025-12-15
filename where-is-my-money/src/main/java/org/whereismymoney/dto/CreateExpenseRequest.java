package org.whereismymoney.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CreateExpenseRequest(
        UUID groupId,
        @NotEmpty
        @NotEmpty
        String expenseName,
        @NotNull
        @NotEmpty
        @Positive
        BigDecimal amount,
        @NotNull
        UUID expenseOwner,
        List<UserExpense> expenseMembers,
        boolean isCustom
) {
}
