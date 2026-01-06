package org.whereismymoney.controllers.v2.dto;

import java.util.List;

public record Debts(
        List<DebtDto> debts
) {
}
