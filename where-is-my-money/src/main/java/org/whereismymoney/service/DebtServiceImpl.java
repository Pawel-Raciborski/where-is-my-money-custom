package org.whereismymoney.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.whereismymoney.model.Debt;
import org.whereismymoney.repository.DebtRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DebtServiceImpl implements DebtService {
    private final DebtRepository debtRepository;

    @Override
    public List<Debt> getGroupDebts(UUID groupId) {
        return debtRepository.findAllByGroup_Id(groupId);
    }
}
