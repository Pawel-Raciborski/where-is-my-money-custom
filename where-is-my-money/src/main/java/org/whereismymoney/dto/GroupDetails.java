package org.whereismymoney.dto;

import org.whereismymoney.model.User;

import java.util.List;
import java.util.UUID;

public record GroupDetails(
        String groupName,
        String ownerFullName,
        String description,
        List<UserDto> members,
        UUID ownerId
) {
}
