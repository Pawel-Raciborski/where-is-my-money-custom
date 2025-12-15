package org.whereismymoney.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.whereismymoney.model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
