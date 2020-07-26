package org.pageObject.Utils;

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
}

