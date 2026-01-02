package org.whereismymoney.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.whereismymoney.model.Expense;

import java.util.List;
import java.util.UUID;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("""
    SELECT e FROM Expense e
    WHERE e.group.id = :groupId
    """)
    List<Expense> findAllGroupExpenses(UUID groupId);
}
