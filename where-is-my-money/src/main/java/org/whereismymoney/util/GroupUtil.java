package org.whereismymoney.util;

import lombok.experimental.UtilityClass;
import org.whereismymoney.model.Group;
import org.whereismymoney.model.Owner;

import java.time.LocalDateTime;

@UtilityClass
public class GroupUtil {
    public Group buildGroup(String name, Owner owner){
        return Group.builder()
                .name(name)
                .owner(owner)
                .creationDate(LocalDateTime.now())
                .build();
    }
}
