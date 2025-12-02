package org.whereismymoney.service;

import org.whereismymoney.dto.CreateGroupRequest;
import org.whereismymoney.model.Group;

public interface GroupService {
    Group create(CreateGroupRequest createGroupRequest);
}
