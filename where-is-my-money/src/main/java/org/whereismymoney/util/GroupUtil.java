package org.whereismymoney.util;

import lombok.experimental.UtilityClass;
import org.whereismymoney.controllers.v2.dto.GroupDto;
import org.whereismymoney.model.Group;
import org.whereismymoney.model.User;

import java.time.LocalDateTime;

@UtilityClass
public class GroupUtil {
    public Group buildGroup(String name, User owner, String description) {
        LocalDateTime localDateTime = LocalDateTime.now();

        return Group.builder()
                .name(name)
                .owner(owner)
                .description(description)
                .creationDate(localDateTime)
                .lastVisitedDate(localDateTime)
                .isActive(true)
                .build();
    }

    public GroupDto mapToDto(Group group){
        return new GroupDto(
                group.getId(),
                group.getName(),
                group.getDescription(),
                group.getCreationDate(),
                group.getLastVisitedDate(),
                group.isActive()
        );
    }
}
