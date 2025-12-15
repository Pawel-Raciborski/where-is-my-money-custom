package org.whereismymoney.model;

import java.util.List;
import java.util.UUID;

public record DebtCalculations(
        UUID userId,
        List<Debt> userDepts
) {
}
