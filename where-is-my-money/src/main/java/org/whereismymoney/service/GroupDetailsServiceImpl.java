package org.whereismymoney.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.whereismymoney.dto.GroupDetails;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupDetailsServiceImpl implements GroupDetailsService {
    private final GroupService groupService;

    @Override
    public GroupDetails getGroupDetails(UUID groupId) {
        return null;
    }
}
