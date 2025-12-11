package org.whereismymoney.service;

import org.springframework.stereotype.Service;
import org.whereismymoney.dto.GroupDetails;

import java.util.UUID;

@Service
public class GroupDetailsServiceImpl implements GroupDetailsService {
    @Override
    public GroupDetails getGroupDetails(UUID groupId, String token) {
        return null;
    }
}
