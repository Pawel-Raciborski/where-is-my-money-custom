package org.whereismymoney.model;

import java.util.List;

public record DebtCalculations(
        List<Debt> debts
) {
}
