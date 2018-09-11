package com.whl.datafiles.customize.daoImpl;

import com.whl.datafiles.customize.annotation.WElement;
import com.whl.datafiles.customize.annotation.WTable;
import com.whl.datafiles.customize.dao.BaseDao;
import org.apache.poi.ss.formula.functions.T;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @Author: Wang HLiang
 * @Description: Create a new file
 * @Date: Created in 14:23 2018-08-01
 * @Modofied:
 */
public class BaseDaoImpl<T> implements BaseDao<T> {
    private Class<?> aClass=null;
    @Override
    public int add(T t) {
        if(t==null){
            return 0;
        }
        aClass = t.getClass();
        boolean annotationPresent = aClass.isAnnotationPresent(WTable.class);
        if(annotationPresent){
            WTable annotation = aClass.getAnnotation(WTable.class);
            //数据库表名
            String tableName = annotation.value();
            Field[] fields = aClass.getDeclaredFields();
            StringBuffer sqlBuffer=new StringBuffer("insert into "+tableName+" ");
            StringBuffer keyBuffer=new StringBuffer("(");
            StringBuffer valueBuffer=new StringBuffer("(");
            for (Field field:
                 fields) {
                field.setAccessible(true);
                boolean annotationPresent1 = field.isAnnotationPresent(WElement.class);
                if(annotationPresent1){
                    WElement annotation1 = field.getAnnotation(WElement.class);
                    //数据库中字段名
                    String keyvalue = annotation1.value();
                    String elemet= null;
                    try {
                        elemet = (String) field.get(t);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    if("(".equals(keyBuffer.toString())){
                        keyBuffer.append(keyvalue);
                        valueBuffer.append("\'"+elemet+"\'");
                    }else{
                        keyBuffer.append(","+keyvalue);
                        valueBuffer.append(",\'"+elemet+"\'");

                    }
                }
            }
            keyBuffer.append(")");
            valueBuffer.append(")");
            sqlBuffer.append(keyBuffer).append(" values ").append(valueBuffer);
            System.out.println(sqlBuffer.toString());
        }
        return 1;
    }

    @Override
    public int delete(T t) {
        return 2;
    }

    @Override
    public T select() {
        return null;
    }

    @Override
    public int update(T t) {
        return 3;
    }
}
