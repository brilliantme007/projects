package com.example.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 王洪亮 on 2017/8/19.
 */
public class DateUtils {

    public static String dateToString(Date date){
        if(date!=null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = sdf.format(date);
            return dateString;
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
