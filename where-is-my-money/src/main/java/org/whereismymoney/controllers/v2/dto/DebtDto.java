package org.whereismymoney.controllers.v2.dto;

import org.whereismymoney.dto.UserDto;

import java.math.BigDecimal;

public record DebtDto(
        Long id,
        UserDto debtor,
        UserDto creditor,
        BigDecimal amount
) {
}
