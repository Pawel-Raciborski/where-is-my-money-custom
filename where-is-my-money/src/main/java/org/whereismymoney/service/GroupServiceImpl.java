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
            owner = userService.create(createGroupRequest.ownerFullName());

            emptyToken.setUser(owner);
            owner.getTokens().add(emptyToken);
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

}
