package org.whereismymoney.util;

import lombok.experimental.UtilityClass;
import org.whereismymoney.controllers.v2.dto.DebtDto;
import org.whereismymoney.model.Debt;

@UtilityClass
public class DebtUtil {

    public DebtDto mapToDto(Debt debt) {
        return new DebtDto(
                debt.getId(),
                UserUtil.mapToDto(debt.getDebtor()),
                UserUtil.mapToDto(debt.getCreditor()),
                debt.getAmount()
        );
    }
}
