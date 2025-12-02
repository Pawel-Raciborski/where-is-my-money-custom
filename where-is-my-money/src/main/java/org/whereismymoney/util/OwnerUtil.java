package org.whereismymoney.util;

import lombok.experimental.UtilityClass;
import org.whereismymoney.model.Owner;

@UtilityClass
public class OwnerUtil {
    public Owner buildOwner(String fullName){
        return Owner.builder()
                .fullName(fullName)
                .build();
    }
}
