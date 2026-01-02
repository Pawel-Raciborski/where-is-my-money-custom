package org.whereismymoney.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.whereismymoney.dto.CreateExpenseRequest;
import org.whereismymoney.model.Expense;
import org.whereismymoney.service.ExpenseService;
import org.whereismymoney.service.GroupService;

import java.util.Objects;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/groups/{groupId}/expenses")
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;

    @PostMapping("/new")
    public String createExpense(
            @PathVariable UUID groupId,
            @Valid CreateExpenseRequest createExpenseRequest,
            Model model,
            @CookieValue(name = "GROUP_TOKEN") String token
    ) {
        log.info("Creating expense: {}", createExpenseRequest);
        String path = "redirect:group/".concat(groupId.toString());

        if (Objects.isNull(token)) {
            return path;
        }

        Expense newExpense = expenseService.createExpense(createExpenseRequest, token, groupId);

        model.addAttribute("expense", newExpense);

        return path.concat("?token=").concat(token);
    }
}
