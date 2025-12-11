package org.whereismymoney.dto;

import java.util.UUID;

public record UserDto(
        UUID id,
        String fullName
) {
}
