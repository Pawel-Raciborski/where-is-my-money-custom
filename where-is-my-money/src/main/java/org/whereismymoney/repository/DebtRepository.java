package org.whereismymoney.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.whereismymoney.model.Debt;

import java.util.Optional;
import java.util.UUID;

public interface DebtRepository extends JpaRepository<Debt, Long> {

    Optional<Debt> findByDebtor_IdAndCreditor_IdAndGroup_Id(UUID debtorId, UUID creditorId, UUID groupId);
}
