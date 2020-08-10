package org.pageObject.Utils;

import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    public static Integer extractIntFromString(String items) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(items);
        Integer extractedInt = null;
        while (matcher.find()) {
            extractedInt = Integer.parseInt(matcher.group());
        }
        return extractedInt;
    }
    public static Double extractDoubleFromString(String price) {
        String priceIgnoreComma = price.replace(",", "");
        Pattern pattern = Pattern.compile("\\d+.\\d+");
        Matcher matcher = pattern.matcher(priceIgnoreComma);
        Double extractedDouble = null;
        while (matcher.find()) {
            extractedDouble = Double.parseDouble(matcher.group());
        }
        return extractedDouble;
    }

    private static final String ALPHABET = "QWERTYUIOPASDFGHJKL0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZqwertyuiopasdfghjklzxcvbnm0987654321";
    private static final SecureRandom RANDOM = new SecureRandom();
    public static String generateRandomString(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; ++i) {
            sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return sb.toString();
    }
}