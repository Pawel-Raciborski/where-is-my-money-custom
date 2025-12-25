package org.whereismymoney.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.whereismymoney.dto.CreateExpenseRequest;
import org.whereismymoney.model.Expense;
import org.whereismymoney.service.ExpenseService;
import org.whereismymoney.service.GroupService;

import java.util.Objects;

@Controller
@RequestMapping("/expenses")
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;

    @PostMapping("/new")
    public String createExpense(
            @Valid CreateExpenseRequest createExpenseRequest,
            Model model,
            @CookieValue(name = "GROUP_TOKEN") String token
    ) {
        if (Objects.isNull(token)) {
            return "redirect:group/".concat(createExpenseRequest.groupId().toString());
        }

        Expense newExpense = expenseService.createExpense(createExpenseRequest, token);

        model.addAttribute("expense", newExpense);

        return "expense_created";
    }
}
