package org.spring_security.whereismymoney.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record CreateGroupRequest(
        @NotEmpty
        String name,
        @NotEmpty
        String ownerFullName,
        @Email
        @NotEmpty
        String email
) {
}
