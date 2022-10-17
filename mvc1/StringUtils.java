package com.zx.mySpring.Utils;

/**
 * @ClassName StringUtils
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/14 9:56
 * @Version 1.0
 */
public class StringUtils {
    public static boolean isEmpty(String s) {
        return " ".equals(s) || null == s;
    }

    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }

}
