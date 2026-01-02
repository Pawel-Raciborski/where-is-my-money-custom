package org.whereismymoney.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.whereismymoney.dto.CreateMemberRequest;
import org.whereismymoney.model.Group;
import org.whereismymoney.model.User;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final UserService userService;
    private final GroupService groupService;

    @Override
    @Transactional
    public User addMemberToGroup(UUID groupId, CreateMemberRequest createMemberRequest, String token) {
        log.info("Searching for group with ID: {} and token: {}", groupId, token);
        Group group = groupService.findGroupWithToken(groupId, token);

        User user = userService.create(createMemberRequest.name(), UUID.randomUUID().toString());
        group.getMembers().add(user);
        return user;
    }

    @Override
    public List<User> getGroupMembers(UUID groupId) {
        return userService.findAllInGroup(groupId);
    }
}
