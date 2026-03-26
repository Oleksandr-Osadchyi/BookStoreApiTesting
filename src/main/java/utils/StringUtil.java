package utils;

import java.util.List;

public class StringUtil {

    public static String buildString(List<String> list, String separator) {
        if (list.isEmpty())
            return "";

        StringBuilder builder = new StringBuilder();
        for (String string : list) {
            builder.append(string).append(separator);
        }
        return builder.toString();
    }
}
