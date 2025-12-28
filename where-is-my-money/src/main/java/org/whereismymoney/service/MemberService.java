package org.whereismymoney.service;

import org.whereismymoney.dto.CreateMemberRequest;
import org.whereismymoney.model.User;

import java.util.UUID;

public interface MemberService {
    User addMemberToGroup(UUID groupId, CreateMemberRequest createMemberRequest, String token);
}
