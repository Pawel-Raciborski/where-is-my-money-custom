package org.whereismymoney.service;

import org.whereismymoney.dto.CreateGroupRequest;
import org.whereismymoney.dto.GroupDetails;
import org.whereismymoney.model.Group;

import java.util.UUID;

public interface GroupService {
    Group create(CreateGroupRequest createGroupRequest, String tokenId);


}
