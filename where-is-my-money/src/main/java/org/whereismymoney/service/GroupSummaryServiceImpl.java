package org.whereismymoney.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.whereismymoney.controllers.v2.dto.GroupSummary;
import org.whereismymoney.model.Debt;
import org.whereismymoney.model.Expense;
import org.whereismymoney.model.Group;
import org.whereismymoney.model.User;
import org.whereismymoney.util.GroupUtil;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupSummaryServiceImpl implements GroupSummaryService {
    private final GroupService groupService;
    private final MemberService memberService;
    private final ExpenseService expenseService;
    private final DebtService debtService;

    @Override
    public GroupSummary getGroupSummary(UUID groupId) {
        Group group = groupService.findById(groupId);
        List<User> groupMembers = memberService.getGroupMembers(groupId);
        List<Expense> groupExpenses = expenseService.getGroupExpenses(groupId);
        List<Debt> groupDebts = debtService.getGroupDebts(groupId);

        return GroupUtil.buildGroupSummary(group, groupMembers, groupExpenses, groupDebts);
    }
}
