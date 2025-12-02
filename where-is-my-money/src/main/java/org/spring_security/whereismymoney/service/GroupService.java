package org.spring_security.whereismymoney.service;

import org.spring_security.whereismymoney.dto.CreateGroupRequest;
import org.spring_security.whereismymoney.model.Group;

public interface GroupService {
    Group create(CreateGroupRequest createGroupRequest);
}
