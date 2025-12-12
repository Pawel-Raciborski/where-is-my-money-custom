package org.whereismymoney.service;

import org.whereismymoney.dto.GroupDetails;

import java.util.UUID;

public interface GroupDetailsService {
    GroupDetails getGroupDetails(UUID groupId);

}

