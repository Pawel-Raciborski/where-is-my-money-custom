package org.whereismymoney.util;

public class VerificationCodeUtil {
    public static String generateCode(int length) {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int digit = (int) (Math.random() * 10);
            code.append(digit);
        }
        return code.toString();
    }
}
