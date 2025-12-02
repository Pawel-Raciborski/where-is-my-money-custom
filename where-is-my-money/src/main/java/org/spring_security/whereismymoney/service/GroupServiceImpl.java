package org.spring_security.whereismymoney.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.spring_security.whereismymoney.dto.CreateGroupRequest;
import org.spring_security.whereismymoney.model.Group;
import org.spring_security.whereismymoney.model.Owner;
import org.spring_security.whereismymoney.repository.GroupRepository;
import org.spring_security.whereismymoney.util.GroupUtil;
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
