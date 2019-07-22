package io.doubleu0714.diary.util;

public abstract class StringUtils {
    public static boolean isEmpty(String s) {
        return "".equals(s) || s == null;
    }

    public static boolean isNotEmpty(String s) {
        return s != null && !"".equals(s);
    }
}