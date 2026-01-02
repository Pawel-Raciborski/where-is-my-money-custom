package org.whereismymoney.util;

import lombok.experimental.UtilityClass;
import org.whereismymoney.dto.UserDto;
import org.whereismymoney.model.User;

@UtilityClass
public class UserUtil {
    public User buildUser(String fullName, String email){
        return User.builder()
                .fullName(fullName)
                .email(email)
                .build();
    }

    public static UserDto mapToDto(User user) {
        return new UserDto(user.getId(), user.getFullName());
    }
}
