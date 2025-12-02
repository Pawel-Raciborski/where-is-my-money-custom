package org.spring_security.whereismymoney.util;

import lombok.experimental.UtilityClass;
import org.spring_security.whereismymoney.model.Group;
import org.spring_security.whereismymoney.model.Owner;

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
