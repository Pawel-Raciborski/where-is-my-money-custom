package org.whereismymoney.util;

public class RandomNumberGenerator {

    public static String generateActivationCode(int length) {
        String numbers = "0123456789";
        StringBuilder activationCode = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * numbers.length());
            activationCode.append(numbers.charAt(index));
        }

        return activationCode.toString();
    }

}
