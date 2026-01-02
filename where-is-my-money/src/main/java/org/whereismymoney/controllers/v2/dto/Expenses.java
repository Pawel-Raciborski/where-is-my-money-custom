package org.whereismymoney.controllers.v2.dto;

import java.util.List;

public record Expenses(
        List<ExpenseDto> expenseDtoList
) {}
