package org.whereismymoney.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.whereismymoney.dto.CreateExpenseRequest;
import org.whereismymoney.model.Expense;
import org.whereismymoney.model.Group;
import org.whereismymoney.model.User;
import org.whereismymoney.model.UserExpense;
import org.whereismymoney.repository.ExpenseRepository;
import org.whereismymoney.util.ExpenseUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
    private final UserService userService;
    private final GroupService groupService;
    private final ExpenseRepository expenseRepository;
    private final UserExpenseService userExpenseService;
    private final DebtCalculationService debtCalculationService;

    @Override
    @Transactional
    public Expense createExpense(CreateExpenseRequest expense, String token, UUID groupId) {
        log.info("Expense creation requested: {} for groupId: {}", expense, groupId);
        User expenseOwner = userService.findById(expense.expenseOwner());
        Group group = groupService.findGroupWithToken(groupId, token);

        BigDecimal totalAmount = expense.expenseMembers().stream()
                .map(org.whereismymoney.dto.UserExpense::price)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        log.info("Calculated total amount from members: {}", totalAmount);
        log.info("Total amount and expense amount: {} - {}", totalAmount, expense.amount());

        if (totalAmount.compareTo(expense.amount()) < 0) {
            throw new RuntimeException("Suma składowa nie zgadza się z całkowitą kwotą!");
        }

        Expense expenseToSave = ExpenseUtil.buildExpense(expense.expenseName(), expenseOwner, expense.amount(), expense.isCustom(), group);
        Expense newExpense = expenseRepository.save(expenseToSave);

        List<UUID> userIds = expense.expenseMembers().stream()
                .map(org.whereismymoney.dto.UserExpense::userId)
                .toList();

        Map<UUID, User> users = userService.findAllByIds(userIds)
                .stream()
                .collect(Collectors.toMap(User::getId, u -> u));

        List<UserExpense> userExpenses = expense.expenseMembers().stream().map(userExpense -> {
            User user = users.get(userExpense.userId());
            BigDecimal amount = userExpense.price();
            return UserExpense.builder()
                    .user(user)
                    .expense(newExpense)
                    .amount(amount)
                    .build();
        }).toList();

        List<UserExpense> savedUserExpenses = userExpenseService.saveAll(userExpenses);
        debtCalculationService.createAndSave(group, newExpense, savedUserExpenses);

        return newExpense;
    }

    @Override
    public List<Expense> getGroupExpenses(UUID groupId) {
        return expenseRepository.findAllGroupExpenses(groupId);
    }
}
