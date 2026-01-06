package org.whereismymoney.service;

import org.whereismymoney.controllers.v2.dto.GroupSummary;
import org.whereismymoney.dto.GroupDetails;

import java.util.UUID;

public interface GroupDetailsService {
    GroupDetails getGroupDetails(UUID groupId);

}

