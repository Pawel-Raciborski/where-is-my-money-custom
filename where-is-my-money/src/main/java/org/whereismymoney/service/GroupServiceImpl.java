package org.whereismymoney.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.whereismymoney.dto.CreateGroupRequest;
import org.whereismymoney.model.Group;
import org.whereismymoney.model.Token;
import org.whereismymoney.model.User;
import org.whereismymoney.repository.GroupRepository;
import org.whereismymoney.util.GroupUtil;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final UserService userService;
    private final GroupRepository groupRepository;
    private final TokenService tokenService;

    @Transactional
    public Group create(CreateGroupRequest createGroupRequest, String token) {
        User owner;
        Optional<User> optionalUser = userService.findByTokenValue(token);
        Token emptyToken = tokenService.findTokenByValue(token)
                .orElse(tokenService.createEmptyToken());
        if (optionalUser.isEmpty()) {
            owner = userService.create(createGroupRequest.ownerFullName(),createGroupRequest.email());

            emptyToken.setUser(owner);
            owner.setToken(emptyToken);
        } else {
            owner = optionalUser.get();
        }
        Group groupToCreate = GroupUtil.buildGroup(createGroupRequest.groupName(), owner, createGroupRequest.description());

        groupToCreate.setToken(emptyToken);
        groupToCreate.getMembers().add(owner);
        Group savedGroup = groupRepository.save(groupToCreate);
        emptyToken.setGroup(savedGroup);
        return savedGroup;
    }

    @Override
    public Group findGroupWithToken(UUID groupId, String tokenValue) {
        return groupRepository.findByIdAndToken(groupId,tokenValue)
                .orElseThrow(() -> new IllegalArgumentException("Group not found with the provided ID and token."));
    }

}
