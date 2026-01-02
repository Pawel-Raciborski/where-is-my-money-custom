package org.whereismymoney.service;

import org.whereismymoney.dto.CreateExpenseRequest;
import org.whereismymoney.model.Expense;

import java.util.List;
import java.util.UUID;

public interface ExpenseService {
    Expense createExpense(CreateExpenseRequest expense, String token, UUID groupId);

    List<Expense> getGroupExpenses(UUID groupId);
}
