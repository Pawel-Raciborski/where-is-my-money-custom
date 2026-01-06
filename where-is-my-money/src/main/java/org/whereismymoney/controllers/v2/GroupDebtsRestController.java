package org.whereismymoney.controllers.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.whereismymoney.controllers.v2.dto.DebtDto;
import org.whereismymoney.controllers.v2.dto.Debts;
import org.whereismymoney.service.DebtCalculationService;
import org.whereismymoney.service.DebtService;
import org.whereismymoney.util.DebtUtil;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(GroupDebtsRestController.API_V_2_GROUPS_GROUP_ID_DEBTS)
@RequiredArgsConstructor
public class GroupDebtsRestController {
    public static final String API_V_2_GROUPS_GROUP_ID_DEBTS = "api/v2/groups/{groupId}/debts";
    private final DebtService debtService;

    @GetMapping
    public ResponseEntity<Debts> getGroupDebts(
            @PathVariable UUID groupId
            ){
        List<DebtDto> debts = debtService.getGroupDebts(groupId).stream().map(DebtUtil::mapToDto).toList();

        return ResponseEntity.ok(new Debts(debts));
    }
}
