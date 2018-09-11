package com.whl.datafiles.utils;

import org.apache.poi.ss.formula.functions.T;
import org.apache.tomcat.jni.Time;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by 王洪亮 on 2017/8/19.
 */
public class DateUtils {

    /**
     * 将时间转换成字符串
     * @param date   Date,TimeStamp
     * @param pattern   时间格式(例:yyyyMMddHHmmss)
     * @return
     */
    public static String dateToString(Object date, String pattern){
        try{
            if(date!=null){
                if(ComUtil.isEmptyOrNullOrAllBlank(pattern)){
                    pattern="yyyy-MM-dd HH:mm:ss";
                }
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                if(date instanceof Date){
                    Date d= (Date) date;
                    return sdf.format(d);
                }

                if(date instanceof Timestamp){
                    Timestamp d= (Timestamp) date;
                    return sdf.format(d);
                }
            }
        }catch(Exception e){
            return null;
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(dateToString(new Date(),"yyyyMMddHHmmss"));
        System.out.println(Pattern.compile("haha.*").matcher("1haha21132").find());
    }
}
