package org.whereismymoney.controllers.v2.dto;

import lombok.Builder;
import org.whereismymoney.dto.UserDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Builder
public record GroupSummary(
        UUID groupId,
        String groupName,
        Integer numberOfMembers,
        Integer numberOfExpenses,
        BigDecimal totalAmount,
        List<UserDto> members,
        List<DebtDto> debts,
        List<ExpenseDto> expenses

) {
}
