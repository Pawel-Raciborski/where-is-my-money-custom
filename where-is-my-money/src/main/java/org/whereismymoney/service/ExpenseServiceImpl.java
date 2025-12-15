package org.whereismymoney.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.whereismymoney.dto.CreateExpenseRequest;
import org.whereismymoney.model.Expense;
import org.whereismymoney.model.Group;
import org.whereismymoney.model.User;
import org.whereismymoney.repository.ExpenseRepository;
import org.whereismymoney.util.ExpenseUtil;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
    private final UserService userService;
    private final GroupService groupService;
    private final ExpenseRepository expenseRepository;
    private final UserExpenseService userExpenseService;

    @Override
    public Expense createExpense(CreateExpenseRequest expense) {
        User expenseOwner = userService.findById(expense.expenseOwner());
        Group group = groupService.findById(expense.groupId());

        Expense expenseToSave = ExpenseUtil.buildExpense(expense.expenseName(), expenseOwner, expense.amount(), expense.isCustom(), group);
        Expense saved = expenseRepository.save(expenseToSave);

        expense.expenseMembers().forEach(userExpense -> {
            User user = userService.findById(userExpense.userId());
            BigDecimal amount = userExpense.price();
            userExpenseService.save(user,saved,amount);
        });

        return saved;
    }
}
