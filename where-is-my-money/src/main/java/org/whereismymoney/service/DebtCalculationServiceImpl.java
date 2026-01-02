package org.whereismymoney.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.whereismymoney.dto.UserBalance;
import org.whereismymoney.model.Debt;
import org.whereismymoney.model.Expense;
import org.whereismymoney.model.Group;
import org.whereismymoney.model.UserExpense;
import org.whereismymoney.repository.DebtRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DebtCalculationServiceImpl implements DebtCalculationService {
    private final DebtRepository debtRepository;

    @Override
    @Transactional
    public void createAndSave(Group group, Expense expense, List<UserExpense> userExpenses) {
        log.info("UserExpenses received for debt calculation: {}", userExpenses.size());
        List<Debt> debts = calculateAndPrepare(expense, userExpenses);
        log.info("Debt calculations prepared: {}", debts.size());

        for (Debt debt : debts) {
            Optional<Debt> optionalDebt = debtRepository.findByDebtor_IdAndCreditor_IdAndGroup_Id(
                    debt.getCreditor().getId(),
                    debt.getDebtor().getId(),
                    group.getId()
            );

            if (optionalDebt.isPresent()) {
                Debt existingDebt = optionalDebt.get();
                existingDebt.setAmount(existingDebt.getAmount().add(debt.getAmount()));
                debtRepository.save(existingDebt);
                continue;
            }

            optionalDebt = debtRepository.findByDebtor_IdAndCreditor_IdAndGroup_Id(
                    debt.getDebtor().getId(),
                    debt.getCreditor().getId(),
                    group.getId()
            );

            if (optionalDebt.isPresent()) {
                Debt existingDebt = optionalDebt.get();
                existingDebt.setAmount(existingDebt.getAmount().subtract(debt.getAmount()));
                debtRepository.save(existingDebt);
                continue;
            }

            debtRepository.save(debt);
        }
    }

    @Override
    public List<Debt> calculateAndPrepare(Expense expense, List<UserExpense> userExpenses) {
        BigDecimal avgAmount = expense.getTotalAmount().divide(BigDecimal.valueOf(userExpenses.size()), 2, RoundingMode.HALF_UP);
        log.info("Average amount per user calculated: {}", avgAmount);
        if (userExpenses.isEmpty()) {
            return Collections.emptyList();
        }

        List<UserBalance> creditors = new ArrayList<>();
        List<UserBalance> debtors = new ArrayList<>();
        for (UserExpense userExpense : userExpenses) {
            BigDecimal balance = userExpense.getAmount().subtract(avgAmount);
            if (balance.compareTo(BigDecimal.ZERO) > 0) {
                creditors.add(new UserBalance(userExpense.getUser(), balance));
            } else if (balance.compareTo(BigDecimal.ZERO) < 0) {
                debtors.add(new UserBalance(userExpense.getUser(), balance.abs()));
            }
        }
        log.info("Users with overpayment: {}, users with dept: {}", creditors.size(), debtors.size());
        List<Debt> debtCalculations = new ArrayList<>();

        int i = 0, j = 0;

        while (i < creditors.size() && i < debtors.size()) {
            UserBalance creditor = creditors.get(i);
            UserBalance debtor = debtors.get(j);

            BigDecimal transfer = creditor.getAmount().min(debtor.getAmount());

            if (transfer.compareTo(BigDecimal.ZERO) > 0) {
                debtCalculations.add(Debt.builder()
                        .creditor(creditor.getUser())
                        .debtor(debtor.getUser())
                        .group(expense.getGroup())
                        .amount(transfer)
                        .build());
            }

            creditor.setAmount(creditor.getAmount().subtract(transfer));
            debtor.setAmount(debtor.getAmount().subtract(transfer));

            if (creditor.getAmount().compareTo(BigDecimal.ZERO) == 0) i++;
            if (debtor.getAmount().compareTo(BigDecimal.ZERO) == 0) j++;

        }
        return debtCalculations;
    }
}
