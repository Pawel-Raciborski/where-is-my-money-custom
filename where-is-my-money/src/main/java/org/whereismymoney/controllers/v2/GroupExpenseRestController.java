package org.whereismymoney.controllers.v2;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.whereismymoney.controllers.v2.dto.ExpenseDto;
import org.whereismymoney.controllers.v2.dto.Expenses;
import org.whereismymoney.dto.CreateExpenseRequest;
import org.whereismymoney.model.Expense;
import org.whereismymoney.service.ExpenseService;
import org.whereismymoney.util.ExpenseUtil;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping(GroupExpenseRestController.API_V_2_GROUPS_GROUP_ID_EXPENSES)
@RequiredArgsConstructor
public class GroupExpenseRestController {
    public static final String API_V_2_GROUPS_GROUP_ID_EXPENSES = "api/v2/groups/{groupId}/expenses";
    private final ExpenseService expenseService;

    @PostMapping("/new")
    public ResponseEntity<?> createExpense(
            @PathVariable UUID groupId,
            @RequestBody @Valid CreateExpenseRequest createExpenseRequest,
            @RequestParam(name = "token") String token
    ) {
        log.info("Creating expense: {}", createExpenseRequest);

        if (Objects.isNull(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Expense newExpense = expenseService.createExpense(createExpenseRequest, token, groupId);
        ExpenseDto expenseDto = ExpenseUtil.mapToDto(newExpense);

        return ResponseEntity.ok(expenseDto);
    }

    @GetMapping
    public ResponseEntity<Expenses> getExpenses(
            @PathVariable UUID groupId
    ) {
        List<ExpenseDto> list = expenseService.getGroupExpenses(groupId).stream()
                .map(ExpenseUtil::mapToDto)
                .toList();

        return ResponseEntity.ok(new Expenses(list));
    }
}
