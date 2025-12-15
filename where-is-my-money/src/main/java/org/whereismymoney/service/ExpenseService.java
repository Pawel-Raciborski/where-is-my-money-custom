package org.whereismymoney.service;

import org.whereismymoney.dto.CreateExpenseRequest;
import org.whereismymoney.model.Expense;

public interface ExpenseService {
    Expense createExpense(CreateExpenseRequest expense);
}
