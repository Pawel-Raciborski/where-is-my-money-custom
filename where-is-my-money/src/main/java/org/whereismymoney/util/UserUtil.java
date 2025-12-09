package org.whereismymoney.util;

import lombok.experimental.UtilityClass;
import org.whereismymoney.model.User;

@UtilityClass
public class UserUtil {
    public User buildUser(String fullName){
        return User.builder()
                .fullName(fullName)
                .build();
    }
}
