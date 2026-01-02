package org.whereismymoney.util;

import lombok.experimental.UtilityClass;
import org.whereismymoney.controllers.v2.dto.ExpenseDto;
import org.whereismymoney.model.Expense;
import org.whereismymoney.model.Group;
import org.whereismymoney.model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@UtilityClass
public class ExpenseUtil {

    public Expense buildExpense(String name, User owner, BigDecimal amount, boolean isCustom, Group group) {
        return Expense.builder()
                .name(name)
                .owner(owner)
                .totalAmount(amount)
                .isCustom(isCustom)
                .group(group)
                .time(LocalDateTime.now())
                .build();
    }

    public static ExpenseDto mapToDto(Expense newExpense) {
        return new ExpenseDto(
                newExpense.getId(),
                newExpense.getName(),
                newExpense.getOwner().getId(),
                newExpense.getTotalAmount(),
                newExpense.isCustom(),
                newExpense.getTime(),
                newExpense.getGroup().getId()
        );
    }
}
