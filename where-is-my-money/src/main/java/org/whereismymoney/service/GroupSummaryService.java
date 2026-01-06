package org.whereismymoney.service;

import org.whereismymoney.controllers.v2.dto.GroupSummary;

import java.util.UUID;

public interface GroupSummaryService {
    GroupSummary getGroupSummary(UUID groupId);
}
