package org.whereismymoney.service;

import org.whereismymoney.model.Expense;
import org.whereismymoney.model.User;
import org.whereismymoney.model.UserExpense;

import java.math.BigDecimal;
import java.util.List;

public interface UserExpenseService {
    UserExpense save(User user, Expense expense, BigDecimal amount);

    List<UserExpense> saveAll(List<UserExpense> userExpenses);
}
