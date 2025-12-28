package org.whereismymoney.util;

import lombok.experimental.UtilityClass;
import org.whereismymoney.model.Token;

import java.util.UUID;

@UtilityClass
public class TokenUtil {
    public static Token buildToken() {
        return Token.builder()
                .value(UUID.randomUUID().toString())
                .build();
    }
}
