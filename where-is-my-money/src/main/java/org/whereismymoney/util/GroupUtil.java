package org.whereismymoney.util;

import lombok.experimental.UtilityClass;
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
}
