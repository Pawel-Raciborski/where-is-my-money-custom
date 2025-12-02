package org.spring_security.whereismymoney.util;

import lombok.experimental.UtilityClass;
import org.spring_security.whereismymoney.model.Owner;

@UtilityClass
public class OwnerUtil {
    public Owner buildOwner(String fullName){
        return Owner.builder()
                .fullName(fullName)
                .build();
    }
}
