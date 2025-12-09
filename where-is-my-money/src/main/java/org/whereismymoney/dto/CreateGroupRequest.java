package org.whereismymoney.dto;

import jakarta.validation.constraints.NotEmpty;

public record CreateGroupRequest(
        @NotEmpty
        String groupName,
        @NotEmpty
        String ownerFullName,
        String description
) {
}
