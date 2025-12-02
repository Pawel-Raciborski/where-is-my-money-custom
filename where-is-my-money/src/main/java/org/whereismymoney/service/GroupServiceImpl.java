package org.whereismymoney.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.whereismymoney.dto.CreateGroupRequest;
import org.whereismymoney.model.Group;
import org.whereismymoney.model.Owner;
import org.whereismymoney.repository.GroupRepository;
import org.whereismymoney.util.GroupUtil;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final OwnerService ownerService;
    private final GroupRepository groupRepository;

    @Transactional
    public Group create(CreateGroupRequest createGroupRequest){
        Owner owner = ownerService.create(createGroupRequest.ownerFullName());
        Group groupToCreate = GroupUtil.buildGroup(createGroupRequest.name(), owner);

        return null;
    }

}
