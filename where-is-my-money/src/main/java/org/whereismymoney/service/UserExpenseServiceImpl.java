package org.whereismymoney.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.whereismymoney.model.Expense;
import org.whereismymoney.model.User;
import org.whereismymoney.model.UserExpense;
import org.whereismymoney.repository.UserExpenseRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserExpenseServiceImpl implements UserExpenseService {
    private final UserExpenseRepository userExpenseRepository;

    @Override
    public UserExpense save(User user, Expense expense, BigDecimal amount) {
        UserExpense userExpense = UserExpense.builder()
                .amount(amount)
                .expense(expense)
                .user(user)
                .build();

        return userExpenseRepository.save(userExpense);

    }

    @Override
    public List<UserExpense> saveAll(List<UserExpense> userExpenses) {
        return userExpenseRepository.saveAll(userExpenses);
    }
}
