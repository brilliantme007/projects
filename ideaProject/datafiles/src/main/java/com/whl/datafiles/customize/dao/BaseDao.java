package com.whl.datafiles.customize.dao;

/**
 * @Author: Wang HLiang
 * @Description: Create a new file
 * @Date: Created in 14:16 2018-08-01
 * @Modofied:
 */
public interface BaseDao<T> {
    int add(T t);
    int delete(T t);
    T select();
    int update(T t);
}
