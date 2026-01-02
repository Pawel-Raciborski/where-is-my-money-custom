package org.whereismymoney.controllers.v2.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record GroupDto(
        UUID id,
        String name,
        String description,
        LocalDateTime creationDate,
        LocalDateTime lastVisitedDate,
        boolean isActive
        ) {
}
