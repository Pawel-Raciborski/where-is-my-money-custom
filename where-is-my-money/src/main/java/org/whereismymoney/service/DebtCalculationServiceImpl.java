package org.whereismymoney.service;

import lombok.RequiredArgsConstructor;
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

@Service
@RequiredArgsConstructor
public class DebtCalculationServiceImpl implements DebtCalculationService {
    private final DebtRepository debtRepository;

    @Override
    public void createAndSave(Group group, Expense expense, List<UserExpense> userExpenses) {
        List<Debt> debts = calculateAndPrepare(expense, userExpenses);

        for(Debt debt : debts){
            Optional<Debt> optionalDebt = debtRepository.findByDebtor_IdAndCreditor_IdAndGroup_Id(
                    debt.getCreditor().getId(),
                    debt.getDebtor().getId(),
                    group.getId()
            );

            if(optionalDebt.isPresent()){
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

            if(optionalDebt.isPresent()){
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

        if (userExpenses.isEmpty()) {
            return Collections.emptyList();
        }

        List<UserBalance> usersWithOverpaymentAmount = userExpenses.stream().filter(
                userExpense -> userExpense.getAmount().compareTo(avgAmount) > 0
        ).map(u -> new UserBalance(u.getUser(), u.getAmount())).toList();
        List<UserBalance> usersWithDeptAmount = userExpenses.stream().filter(
                userExpense -> userExpense.getAmount().compareTo(avgAmount) < 0
        ).map(u -> new UserBalance(u.getUser(), u.getAmount())).toList();

        List<Debt> debtCalculations = new ArrayList<>();

        int i = 0, j = 0;

        while (i < usersWithOverpaymentAmount.size() && i < usersWithDeptAmount.size()) {
            UserBalance creditor = usersWithOverpaymentAmount.get(i);
            UserBalance debtor = usersWithDeptAmount.get(j);

            BigDecimal transfer = creditor.getAmount().min(debtor.getAmount());

            if (transfer.compareTo(BigDecimal.ZERO) <= 0) {
                debtCalculations.add(Debt.builder()
                        .creditor(creditor.getUser())
                        .debtor(debtor.getUser())
                        .group(expense.getGroup())
                        .amount(transfer)
                        .build());
            }

            creditor.setAmount(creditor.getAmount().subtract(transfer));
            debtor.setAmount(debtor.getAmount().add(transfer));

            if (creditor.getAmount().compareTo(BigDecimal.ZERO) <= 0) i++;
            if (debtor.getAmount().compareTo(BigDecimal.ZERO) <= 0) j++;

        }
        return debtCalculations;
    }
}
