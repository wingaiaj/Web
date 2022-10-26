package com.zx.myssm.Utils;

/**
 * @ClassName StringUtils
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/22 21:23
 * @Version 1.0
 */
public class StringUtils {

    public static boolean isEmpty(String s) {
        if ("".equals(s) || null == s) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String s) {
        if (isEmpty(s)) {

            return false;
        }
        return true;

    }

}
