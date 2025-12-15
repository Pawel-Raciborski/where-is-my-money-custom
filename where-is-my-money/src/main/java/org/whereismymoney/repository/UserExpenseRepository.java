package org.whereismymoney.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.whereismymoney.model.UserExpense;

public interface UserExpenseRepository extends JpaRepository<UserExpense,Long> {
}
