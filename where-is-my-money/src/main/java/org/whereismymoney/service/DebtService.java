package org.whereismymoney.service;

import org.whereismymoney.model.Debt;

import java.util.List;
import java.util.UUID;

public interface DebtService {
    List<Debt> getGroupDebts(UUID groupId);
}
