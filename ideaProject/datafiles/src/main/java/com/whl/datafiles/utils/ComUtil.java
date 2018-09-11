package com.whl.datafiles.utils;

public class ComUtil {
    /**
     * 判断字符串是null or "" or 全是空格组成
     * @param s
     * @return
     */
    public static boolean isEmptyOrNullOrAllBlank(String s){
        return s==null||"".equals(s)||"".equals(s.trim());
    }

    /**
     * 判断字符串是null or ""
     * @param s
     * @return
     */
    public static boolean isEmptyOrNull(String s){
        return s==null||"".equals(s);
    }
}
