package org.whereismymoney.util;

import lombok.experimental.UtilityClass;
import org.whereismymoney.controllers.v2.dto.*;
import org.whereismymoney.model.Debt;
import org.whereismymoney.model.Expense;
import org.whereismymoney.model.Group;
import org.whereismymoney.model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@UtilityClass
public class GroupUtil {
    public Group buildGroup(String name, User owner, String description) {
        LocalDateTime localDateTime = LocalDateTime.now();

        return Group.builder()
                .name(name)
                .owner(owner)
                .description(description)
                .creationDate(localDateTime)
                .lastVisitedDate(localDateTime)
                .isActive(true)
                .build();
    }

    public GroupDto mapToDto(Group group){
        return new GroupDto(
                group.getId(),
                group.getName(),
                group.getDescription(),
                group.getCreationDate(),
                group.getLastVisitedDate(),
                group.isActive()
        );
    }

    public static GroupSummary buildGroupSummary(Group group, List<User> groupMembers, List<Expense> groupExpenses, List<Debt> groupDebts) {
        return GroupSummary.builder()
                .groupId(group.getId())
                .groupName(group.getName())
                .numberOfMembers(groupMembers.size())
                .numberOfExpenses(groupExpenses.size())
                .totalAmount(groupExpenses.stream().reduce(BigDecimal.ZERO,(prev,curr) -> prev.add(curr.getTotalAmount()), BigDecimal::add))
                .debts(groupDebts.stream().map(DebtUtil::mapToDto).toList())
                .members(groupMembers.stream().map(UserUtil::mapToDto).toList())
                .expenses(groupExpenses.stream().map(ExpenseUtil::mapToDto).toList())
                .build();
    }
}
