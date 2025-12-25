package org.whereismymoney.service;

import org.whereismymoney.model.Debt;
import org.whereismymoney.model.Expense;
import org.whereismymoney.model.Group;
import org.whereismymoney.model.UserExpense;

import java.util.List;

public interface DebtCalculationService {
    void createAndSave(Group group, Expense expense, List<UserExpense> userExpenses);
    List<Debt> calculateAndPrepare(Expense expense, List<UserExpense> userExpenses);
}
