package org.whereismymoney.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.whereismymoney.dto.GroupDetails;
import org.whereismymoney.dto.UserDto;
import org.whereismymoney.model.Group;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupDetailsServiceImpl implements GroupDetailsService {
    private final GroupService groupService;

    @Override
    public GroupDetails getGroupDetails(UUID groupId) {
        Group group = groupService.findById(groupId);
        List<UserDto> list = group.getMembers().stream().map(u -> new UserDto(u.getId(), u.getFullName())).toList();

        return new GroupDetails(
                group.getName(),
                group.getOwner().getFullName(),
                group.getDescription(),
                list,
                group.getOwner().getId()
        );
    }
}
